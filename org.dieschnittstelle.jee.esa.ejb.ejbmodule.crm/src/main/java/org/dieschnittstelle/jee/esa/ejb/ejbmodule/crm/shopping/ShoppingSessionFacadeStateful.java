package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.*;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.ProductCRUDLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.ProductCRUDStateless;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.Campaign;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.ProductBundle;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class ShoppingSessionFacadeStateful implements ShoppingSessionFacadeRemote{

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ShoppingSessionFacadeStateful.class);

    @EJB
    private ShoppingCartLocal shoppingCart;

    @EJB
    private CustomerTrackingLocal customerTrackingSystem;

    @EJB
    private CampaignTrackingLocal campaignTrackingSystem;

    @EJB
    private ProductCRUDLocal productCRUD;

    @EJB
    private StockSystemLocal stockSystemLocal;

    private Customer customer;
    private AbstractTouchpoint touchpoint;
    private List<AbstractProduct> products;
    private boolean finished = false;

    @Override
    public void setTouchpoint(AbstractTouchpoint touchpoint) {
        this.touchpoint = touchpoint;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void addProduct(AbstractProduct product, int units) {
        this.shoppingCart.addItem(new ShoppingCartItem(product.getId(), units, product instanceof Campaign));
    }

    @Override
    public void purchase() throws ShoppingException {
        logger.info("purchase()");
        if(this.customer == null || this.touchpoint == null){
            throw new RuntimeException("cannot commit shopping session! Either customer or touchpint has not been set; " + this.customer + " " + this.touchpoint);
        }

        verifyCampaigns();
        checkAndRemoveProductsFromStock();
        List<ShoppingCartItem> products = this.shoppingCart.getItems();
        CustomerTransaction transaction = new CustomerTransaction(this.customer, this.touchpoint, products);
        transaction.setCompleted(true);
        customerTrackingSystem.createTransaction(transaction);
        logger.info("purchase() done.\n");
        finished = true;
    }

    @PreDestroy
    private void finish(){
        if(!finished){
            CustomerTransaction transaction = new CustomerTransaction(customer, touchpoint, shoppingCart.getItems());
            customerTrackingSystem.createTransaction(transaction);
        }
    }

    private void verifyCampaigns() throws ShoppingException{
        if(this.customer == null || this.touchpoint == null){
            throw new RuntimeException("cannot verify campaign! No touchpoint or customer has been set!");
        }

        for(ShoppingCartItem item : this.shoppingCart.getItems()){
            if(item.isCampaign()){
                int availableCampaigns = this.campaignTrackingSystem.existsValidCampaignExecutionAtTouchpoint(item.getErpProductId(), this.touchpoint);
                logger.info("get available campaigns for product " + item.getErpProductId() + ": " + availableCampaigns);
                if(availableCampaigns < item.getUnits()){
                    throw new ShoppingException("verifyCampaigns() failed for productBundle + " + item + " at touchpoint " + this.touchpoint + "! Need " + item.getUnits() + " instances of campaign, but only get: " + availableCampaigns);
                }
            }
        }
    }

    private void checkAndRemoveProductsFromStock(){
        logger.info("checkAndRemoveProductsFromStock()");
        for(ShoppingCartItem item : this.shoppingCart.getItems()){
            AbstractProduct product = productCRUD.readProduct(item.getErpProductId());
            if(product == null){
                throw new RuntimeException("The product in the shoppingcart does not exsist! " + item);
            }
            if(item.isCampaign() && product instanceof Campaign){
                this.campaignTrackingSystem.purchaseCampaignAtTouchpoint(item.getErpProductId(), this.touchpoint, item.getUnits());
                Campaign campaign = (Campaign) product;
                for(ProductBundle bundle : campaign.getBundles()){
                    int count = bundle.getUnits() * item.getUnits();
                    int available = stockSystemLocal.getUnitsOnStock(bundle.getProduct(), touchpoint.getErpPointOfSaleId());
                    if(available >= count){
                        stockSystemLocal.removeFromStock(bundle.getProduct(), touchpoint.getErpPointOfSaleId(), count);
                    }
                }
            }else{
                if(product instanceof IndividualisedProductItem){
                    IndividualisedProductItem individualProd = (IndividualisedProductItem) product;
                    int available = stockSystemLocal.getUnitsOnStock(individualProd, touchpoint.getErpPointOfSaleId());
                    if(available >= item.getUnits()){
                        stockSystemLocal.removeFromStock(individualProd, touchpoint.getErpPointOfSaleId(), item.getUnits());
                    }
                }
            }
        }
    }
}

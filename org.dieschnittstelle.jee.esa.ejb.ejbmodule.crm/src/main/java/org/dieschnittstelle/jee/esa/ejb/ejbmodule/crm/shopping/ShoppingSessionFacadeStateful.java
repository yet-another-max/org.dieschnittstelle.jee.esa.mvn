package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.*;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.Campaign;

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

    private Customer customer;
    private AbstractTouchpoint touchpoint;
    private List<AbstractProduct> products;

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
            if(item.isCampaign()){
                this.campaignTrackingSystem.purchaseCampaignAtTouchpoint(item.getErpProductId(), this.touchpoint, item.getUnits());
            }else{

            }
        }
    }
}

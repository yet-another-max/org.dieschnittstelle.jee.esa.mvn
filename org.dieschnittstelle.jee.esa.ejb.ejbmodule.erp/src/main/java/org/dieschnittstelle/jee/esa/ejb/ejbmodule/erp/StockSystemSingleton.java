package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.PointOfSaleCRUDStateless;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.StockItemCRUDStateless;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;
import org.dieschnittstelle.jee.esa.entities.erp.StockItem;

import javax.ejb.*;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Singleton
@WebService(endpointInterface = "org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.StockSystemRemote")
public class StockSystemSingleton implements StockSystemRemote, StockSystemLocal{

    @EJB
    StockItemCRUDLocal stockItemCRUD;

    @EJB
    PointOfSaleCRUDLocal posCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        if(pos != null){
            StockItem item = stockItemCRUD.readStockItem(product, pos);
            if(item == null){
                item = new StockItem();
                item.setProduct(product);
                item.setPos(pos);
                item.setUnits(units);
                stockItemCRUD.createStockItem(item);
            }else{
                item.setUnits(item.getUnits() + units);
                stockItemCRUD.updateStockItem(item);
            }
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        this.addToStock(product, pointOfSaleId, 0 - units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List<IndividualisedProductItem> products;
        products = new ArrayList<IndividualisedProductItem>();
        if(pos != null){
            List<StockItem> stockItems =  stockItemCRUD.readStockItemsForPointOfSale(pos);
            for(StockItem item : stockItems)
                products.add(item.getProduct());
        }
        return products;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List<PointOfSale> points = posCRUD.readAllPointsOfSale();
        List<IndividualisedProductItem> result = new ArrayList<IndividualisedProductItem>();
        if(points != null){
            for(PointOfSale pos : points){
                List<StockItem> items = stockItemCRUD.readStockItemsForPointOfSale(pos);
                for(StockItem item : items){
                    if(!result.contains(item.getProduct()))
                        result.add(item.getProduct());
                }
            }
        }
        return result;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        if(pos != null){
            StockItem item = stockItemCRUD.readStockItem(product, pos);
            if(item != null)
                return item.getUnits();
        }
        return 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<StockItem> items = stockItemCRUD.readStockItemsForProduct(product);
        int count = 0;
        if(items != null){
            for(StockItem item : items){
                count += item.getUnits();
            }
        }
        return count;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<Long> result = new ArrayList<Long>();
        List<StockItem> items = stockItemCRUD.readStockItemsForProduct(product);
        if(items != null){
            for(StockItem item : items){
                result.add(item.getPos().getId());
            }
        }
        return result;
    }

    @Override
    public List<StockItem> getCompleteStock() {
        return stockItemCRUD.readAllStockItems();
    }
}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;
import org.dieschnittstelle.jee.esa.entities.erp.ProductAtPosPK;
import org.dieschnittstelle.jee.esa.entities.erp.StockItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StockItemCRUDStateless implements StockItemCRUDLocal{

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
        em.merge(item.getProduct());
        em.merge(item.getPos());
        em.merge(item);
        return item;
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        ProductAtPosPK key = new ProductAtPosPK(prod, pos);
        StockItem result = em.find(StockItem.class, key);
        return result;
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        em.merge(item);
        return item;
    }

    @Override
    public List<StockItem> readAllStockItems() {
        return em.createQuery("FROM StockItem").getResultList();
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        return em.createQuery("FROM StockItem item WHERE item.product = " + Long.toString(prod.getId())).getResultList();
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        return em.createQuery("FROM StockItem item WHERE item.pos = " + Long.toString(pos.getId())).getResultList();
    }
}

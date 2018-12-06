package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote{

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager oemManager;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        oemManager.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        return oemManager.createQuery("SELECT p FROM AbstractProduct AS p").getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        oemManager.merge(update);
        return update;
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return oemManager.find(AbstractProduct.class, productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        AbstractProduct target = oemManager.find(AbstractProduct.class, productID);
        if(target == null)
            return false;
        oemManager.remove(target);
        return true;
    }
}

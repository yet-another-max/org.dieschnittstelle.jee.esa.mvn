package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by master on 20.02.17.
 *
 * actually, this is a CRUD ejb that uses the entity manager for persisting shopping cart instances. Note, however, that the ShoppingCart class itself is not exposed via the REST interface
 */
@Stateless
public class ShoppingCartRESTServiceImpl implements ShoppingCartRESTService {

    @PersistenceContext(unitName = "crm_PU")
    private EntityManager em;

    @Override
    public long createNewCart() {
        ShoppingCartStateful sc = new ShoppingCartStateful();
        em.persist(sc);
        return sc.getId();
    }

    // note that it is not necessary to explicitly call merge, as merging will done automatically once the transaction associated with this method is committed
    @Override
    public void addProductBundle(long cartId, CrmProductBundle product) {
        em.find(ShoppingCartStateful.class,cartId).addProductBundle(product);
    }

    @Override
    public List<CrmProductBundle> getProductBundles(long cartId) {
        return em.find(ShoppingCartStateful.class,cartId).getProductBundles();
    }

    @Override
    public boolean deleteCart(long cartId) {
        em.remove(em.find(ShoppingCartStateful.class,cartId));
        return true;
    }
}

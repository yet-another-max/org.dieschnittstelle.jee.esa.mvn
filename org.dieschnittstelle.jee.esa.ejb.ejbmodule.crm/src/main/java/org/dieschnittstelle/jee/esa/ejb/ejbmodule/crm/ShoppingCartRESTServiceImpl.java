package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by master on 20.02.17.
 *
 * actually, this is a CRUD ejb that uses the entity manager for persisting shopping cart instances. Note, however, that the ShoppingCart class itself is not exposed via the REST interface
 */
@Singleton
@Startup
public class ShoppingCartRESTServiceImpl implements ShoppingCartRESTService {

    protected static Logger logger = Logger.getLogger(ShoppingCartRESTServiceImpl.class);

    @PersistenceContext(unitName = "crm_PU")
    private EntityManager em;

    public ShoppingCartRESTServiceImpl() {
        logger.info("<constructor>");
    }

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

    @Schedule(second="*")
    public void removeIdleCarts() {
        System.err.println("removeIdleCarts()");

        // use some hard-coded timeout
        long timeout = 10000;

        // read all carts
        for (ShoppingCartStateful scart : (List<ShoppingCartStateful>)em.createQuery("from ShoppingCartStateful").getResultList()) {
            if (System.currentTimeMillis() - scart.getLastUpdated() > timeout) {
                logger.info("ShoppingCart has exceeded idle time. Will remove it: " + scart);
                deleteCart(scart.getId());
            }
            else {
                logger.debug("ShoppingCart has not yet exceeded idle time. Keep it: " + scart);
            }
        }

    }


}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

import javax.annotation.Resource;
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

    // here, the value of the env-entry idle-timeout specified in ejb-jar.xml will be injected
    @Resource(name = "idle-timeout")
    private long idleTimeout;

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

    // if a task shall be scheduled every couple of seconds, also hour and minute need to be specied as "any" ('*')
    // because these attributes default to 0
    @Schedule(second="*/10", hour="*", minute = "*", persistent = false)
    public void removeIdleCarts() {
        logger.info("removeIdleCarts(): idleTimeout is set to: " + idleTimeout);

        // read all carts
        for (ShoppingCartStateful scart : (List<ShoppingCartStateful>)em.createQuery("from ShoppingCartStateful").getResultList()) {
            if (System.currentTimeMillis() - scart.getLastUpdated() > idleTimeout) {
                logger.info("ShoppingCart has exceeded idle time. Will remove it: " + scart.getId());
                deleteCart(scart.getId());
            }
            else {
                logger.debug("ShoppingCart has not yet exceeded idle time. Keep it: " + scart.getId());
            }
        }

    }


}

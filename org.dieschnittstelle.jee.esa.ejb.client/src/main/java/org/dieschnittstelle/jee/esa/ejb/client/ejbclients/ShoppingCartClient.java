package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingCartRESTService;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingCartRemote;
import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class ShoppingCartClient implements ShoppingCartRemote {

	private ShoppingCartRemote ejbProxy;

	private ShoppingCartRESTService serviceProxy;

	// if we are using the REST service rather than the stateful ejb, we will manage the "session id"
	// (= the id of the ShoppingCart entity) manually. In case we use the stateful ejb this will be managed inside of
	// the ejb proxy
	private long shoppingCartEntityId;

	public ShoppingCartClient() throws Exception {

		// we will use the ejb if ejbs shall be used by default
		if (!EJBProxyFactory.getInstance().usesWebAPIAsDefault()) {
			this.ejbProxy = EJBProxyFactory.getInstance().getProxy(ShoppingCartRemote.class, Constants.SHOPPING_CART_BEAN_URI, false);
		}
		else {
			this.serviceProxy = EJBProxyFactory.getInstance().getProxy(ShoppingCartRESTService.class,null,true);
			// a client will be instantiated for each new shopping cart, i.e. we will obtain a cart id here
			this.shoppingCartEntityId = this.serviceProxy.createNewCart();
		}
	}

	@Override
	public void addProductBundle(CrmProductBundle product) {
		if (ejbProxy != null) {
			ejbProxy.addProductBundle(product);
		}
		else {
			serviceProxy.addProductBundle(this.shoppingCartEntityId,product);
		}
	}

	@Override
	public List<CrmProductBundle> getProductBundles() {
		if (ejbProxy != null) {
			return ejbProxy.getProductBundles();
		}
		else {
			return serviceProxy.getProductBundles(this.shoppingCartEntityId);
		}
	}

}

package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingCartRemote;
import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class ShoppingCartClient implements ShoppingCartRemote {

	private ShoppingCartRemote ejbProxy;

	public ShoppingCartClient() throws Exception {
		// for the time being, we will always use the ejb here...
		this.ejbProxy = EJBProxyFactory.getInstance().getProxy(ShoppingCartRemote.class,Constants.SHOPPING_CART_BEAN, false);
	}

	@Override
	public void addProductBundle(CrmProductBundle product) {
		ejbProxy.addProductBundle(product);
	}

	@Override
	public List<CrmProductBundle> getProductBundles() {
		return ejbProxy.getProductBundles();
	}

}

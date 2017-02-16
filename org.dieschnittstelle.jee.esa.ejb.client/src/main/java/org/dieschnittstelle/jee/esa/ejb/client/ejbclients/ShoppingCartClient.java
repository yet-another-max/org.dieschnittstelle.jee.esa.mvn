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
		Context context = new InitialContext();
		this.ejbProxy = (ShoppingCartRemote) context
				.lookup(Constants.SHOPPING_CART_BEAN);
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

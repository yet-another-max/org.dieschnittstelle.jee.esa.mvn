package org.dieschnittstelle.jee.esa.ejb.client.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

public class ShoppingSessionFacadeClient implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShoppingSessionFacadeClient.class);

	private ShoppingSessionFacadeRemote proxy;

	public ShoppingSessionFacadeClient() {
		EJBProxyFactory.initialise();
		this.proxy = EJBProxyFactory.getInstance().getProxy(ShoppingSessionFacadeRemote.class, Constants.SHOPPING_SESSION_FACADE_BEAN_URI);
	}

	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
	    proxy.setTouchpoint(touchpoint);
	}

	@Override
	public void setCustomer(Customer customer) {
		proxy.setCustomer(customer);
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
		proxy.addProduct(product, units);
	}

	@Override
	public void purchase() throws ShoppingException {
		proxy.purchase();
	}

}

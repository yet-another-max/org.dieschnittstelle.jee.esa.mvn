package org.dieschnittstelle.jee.esa.ejb.client.shopping;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

public class ShoppingSessionFacadeClient implements ShoppingBusinessDelegate {

	protected static Logger logger = Logger
			.getLogger(ShoppingSessionFacadeClient.class);

	/*
	 * use a proxy for the ShoppingSessionFacadeRemote interface
	 */

	public ShoppingSessionFacadeClient() {
		/* instantiate the proxy using the EJBProxyFactory (see the other client classes) */
	}

	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
	
	}

	@Override
	public void setCustomer(Customer customer) {
	
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
	
	}

	@Override
	public void purchase() throws ShoppingException {
	
	}

}

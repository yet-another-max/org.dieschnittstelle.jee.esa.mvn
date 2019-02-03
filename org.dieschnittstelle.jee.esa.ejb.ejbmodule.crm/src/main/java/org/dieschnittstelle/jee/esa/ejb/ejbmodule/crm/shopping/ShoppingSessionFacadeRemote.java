package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.annotation.PreDestroy;
import javax.ejb.Remote;

@Remote
public interface ShoppingSessionFacadeRemote {

	public void setTouchpoint(AbstractTouchpoint touchpoint);
	
	public void setCustomer(Customer customer);
	
	public void addProduct(AbstractProduct product, int units);
	
	public void purchase() throws ShoppingException;

	/*
	@PreDestroy
	public void finish();
	*/
	
}

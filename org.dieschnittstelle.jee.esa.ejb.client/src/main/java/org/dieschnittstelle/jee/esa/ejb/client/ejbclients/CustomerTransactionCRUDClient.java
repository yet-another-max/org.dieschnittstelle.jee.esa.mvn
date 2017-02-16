package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud.CustomerTransactionCRUDRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CustomerTransactionCRUDClient implements CustomerTransactionCRUDRemote {

	private CustomerTransactionCRUDRemote ejbProxy;
	
	public CustomerTransactionCRUDClient() throws Exception {
		Context context = new InitialContext();		
		this.ejbProxy = (CustomerTransactionCRUDRemote)context.lookup(Constants.TRANSACTIONS_CRUD_BEAN);
	}
	
	@Override
	public Collection<CustomerTransaction> readAllTransactionsForTouchpoint(
			AbstractTouchpoint touchpoint) {
		return ejbProxy.readAllTransactionsForTouchpoint(touchpoint);
	}

	@Override
	public Collection<CustomerTransaction> readAllTransactionsForCustomer(
			Customer customer) {
		return ejbProxy.readAllTransactionsForCustomer(customer);
	}

	@Override
	public Collection<CustomerTransaction> readAllTransactionsForTouchpointAndCustomer(
			AbstractTouchpoint touchpoint, Customer customer) {
		return ejbProxy.readAllTransactionsForTouchpointAndCustomer(touchpoint, customer);
	}

}

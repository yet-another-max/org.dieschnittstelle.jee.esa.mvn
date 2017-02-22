package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud.CustomerTransactionCRUDRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CustomerTransactionCRUDClient implements CustomerTransactionCRUDRemote {

	protected static Logger logger = Logger.getLogger(CustomerTransactionCRUDClient.class);

	private CustomerTransactionCRUDRemote ejbProxy;
	
	public CustomerTransactionCRUDClient() throws Exception {
		this.ejbProxy = EJBProxyFactory.getInstance().getProxy(CustomerTransactionCRUDRemote.class,Constants.TRANSACTIONS_CRUD_BEAN_URI);
	}
	
	@Override
	public Collection<CustomerTransaction> readAllTransactionsForTouchpoint(
			AbstractTouchpoint touchpoint) {
		try {
			return ejbProxy.readAllTransactionsForTouchpoint(touchpoint);
		}
		catch (Exception e) {
			logger.warn("readAllTransactionsForTouchpoint(): got exception: " + e + ". Look at server-side log for further information");
			return new ArrayList<CustomerTransaction>();
		}
	}

	@Override
	public Collection<CustomerTransaction> readAllTransactionsForCustomer(
			Customer customer) {
		try {
			return ejbProxy.readAllTransactionsForCustomer(customer);
		}
		catch (Exception e) {
			logger.warn("readAllTransactionsForCustomer(): got exception: " + e + ". Look at server-side log for further information");
			return new ArrayList<CustomerTransaction>();
		}
	}

	@Override
	public Collection<CustomerTransaction> readAllTransactionsForTouchpointAndCustomer(
			AbstractTouchpoint touchpoint, Customer customer) {
		// this method is currently not supported by the rest interface
		if (EJBProxyFactory.getInstance().usesWebAPIAsDefault()) {
			logger.warn("readAllTransactionsForTouchpointAndCustomer(): ignore as operation is currently not supported by web api");
			return new ArrayList<CustomerTransaction>();
		}
		return ejbProxy.readAllTransactionsForTouchpointAndCustomer(touchpoint, customer);
	}

}

package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.CustomerTrackingRemote;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CustomerTrackingClient implements CustomerTrackingRemote {

	private CustomerTrackingRemote proxy;
	
	public CustomerTrackingClient() throws Exception {
		Context context = new InitialContext();
		proxy = (CustomerTrackingRemote) context
				.lookup(Constants.CUSTOMER_TRACKING_BEAN);
	}
	
	@Override
	public void createTransaction(CustomerTransaction transaction) {
		proxy.createTransaction(transaction);
	}

	@Override
	public List<CustomerTransaction> readAllTransactions() {
		return proxy.readAllTransactions();
	}

}

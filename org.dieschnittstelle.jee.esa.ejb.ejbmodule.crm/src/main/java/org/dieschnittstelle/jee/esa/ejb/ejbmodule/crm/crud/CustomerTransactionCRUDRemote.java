package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud;

import java.util.Collection;

import javax.ejb.Remote;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;

@Remote
public interface CustomerTransactionCRUDRemote {
	
	public Collection<CustomerTransaction> readAllTransactionsForTouchpoint(AbstractTouchpoint touchpoint);

	public Collection<CustomerTransaction> readAllTransactionsForCustomer(Customer customer);

	public Collection<CustomerTransaction> readAllTransactionsForTouchpointAndCustomer(AbstractTouchpoint touchpoint,Customer customer);

}

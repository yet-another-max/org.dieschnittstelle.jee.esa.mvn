package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud;

import java.util.Collection;

import javax.ejb.Local;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;

@Local
public interface CustomerTransactionCRUDLocal {
	
	public boolean createTransaction(CustomerTransaction transaction);

	public Collection<CustomerTransaction> readAllTransactionsForTouchpoint(AbstractTouchpoint touchpoint);

	public Collection<CustomerTransaction> readAllTransactionsForCustomer(Customer customer);

	public Collection<CustomerTransaction> readAllTransactionsForTouchpointAndCustomer(AbstractTouchpoint touchpoint,Customer customer);

}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud;

import javax.ejb.Remote;

import org.dieschnittstelle.jee.esa.entities.crm.Customer;

@Remote
public interface CustomerCRUDRemote {	
	
	public Customer createCustomer(Customer customer);

	public Customer readCustomer(long id);

	public Customer updateCustomer(Customer customer);
		
	public Customer updateCustomerWithSleep(Customer customer,long sleep);
	
	public boolean deleteCustomer(int id);

	public Customer readCustomerForEmail(String email);

}

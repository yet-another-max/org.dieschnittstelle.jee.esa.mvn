package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerTrackingLocal {
    public void createTransaction(CustomerTransaction transaction);
    public List<CustomerTransaction> readAllTransactions();
}

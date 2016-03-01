package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import java.util.List;
import javax.ejb.Local;

import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

@Local
public interface ShoppingCartLocal {

	public void addProductBundle(CrmProductBundle product);
	
	public List<CrmProductBundle> getProductBundles();

}

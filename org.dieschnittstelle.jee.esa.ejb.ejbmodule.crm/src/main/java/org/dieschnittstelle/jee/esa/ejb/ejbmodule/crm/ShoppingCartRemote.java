package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import java.util.List;

import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

import javax.ejb.Remote;

@Remote
public interface ShoppingCartRemote {

	public void addProductBundle(CrmProductBundle product);
	
	public List<CrmProductBundle> getProductBundles();
	
}

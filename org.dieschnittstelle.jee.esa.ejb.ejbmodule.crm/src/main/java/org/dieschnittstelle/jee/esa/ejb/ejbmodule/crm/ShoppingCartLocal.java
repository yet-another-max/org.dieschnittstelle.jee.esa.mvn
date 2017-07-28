package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import java.util.List;
import javax.ejb.Local;

import org.dieschnittstelle.jee.esa.entities.crm.ShoppingCartItem;

@Local
public interface ShoppingCartLocal {

	public void addItem(ShoppingCartItem product);
	
	public List<ShoppingCartItem> getItems();

}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud;

import java.util.List;

import javax.ejb.Local;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;

@Local
public interface TouchpointCRUDLocal {
	
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint Touchpoint) throws ShoppingException;

	public AbstractTouchpoint readTouchpoint(long id);

	public List<AbstractTouchpoint> readAllTouchpoints();
	
	public AbstractTouchpoint updateTouchpoint(AbstractTouchpoint Touchpoint);
		
	public boolean deleteTouchpoint(int id);

}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import javax.ejb.Remote;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;

@Remote
public interface PointOfSaleCRUDRemote {

	public PointOfSale createPointOfSale(PointOfSale pos);

	public PointOfSale readPointOfSale(long posId);

	public boolean deletePointOfSale(long posId);

}

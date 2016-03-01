package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import java.util.List;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

public interface ProductCRUDRemote {

	public AbstractProduct createProduct(AbstractProduct prod);

	public List<AbstractProduct> readAllProducts();

	public AbstractProduct updateProduct(AbstractProduct update);

	public AbstractProduct readProduct(long productID);

	public boolean deleteProduct(long productID);

}

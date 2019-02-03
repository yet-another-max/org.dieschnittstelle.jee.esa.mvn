package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductCRUDLocal {
    public AbstractProduct createProduct(AbstractProduct prod);
    public List<AbstractProduct> readAllProducts();
    public AbstractProduct updateProduct(AbstractProduct prod);
    public AbstractProduct readProduct(long productID);
    public boolean deleteProduct(long productID);
}

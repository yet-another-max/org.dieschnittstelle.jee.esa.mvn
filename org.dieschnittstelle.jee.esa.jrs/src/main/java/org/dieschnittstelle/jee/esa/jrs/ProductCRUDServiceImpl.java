package org.dieschnittstelle.jee.esa.jrs;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/*
UE JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDServiceImpl.class);

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request){
		logger.info("<construcotr>: " + servletContext + "/" + request);
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");
		logger.info("read out the productCRUD from the servlet context: " + this.productCRUD);
	}

	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {
	    return (AbstractProduct)productCRUD.createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
	    return (List)productCRUD.readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id,
			AbstractProduct update) {
	    return (AbstractProduct)productCRUD.updateObject(update);
	}

	@Override
	public boolean deleteProduct(long id) {
	    return productCRUD.deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
	    return (AbstractProduct)productCRUD.readObject(id);
	}
	
}

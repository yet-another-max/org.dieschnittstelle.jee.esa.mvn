package org.dieschnittstelle.jee.esa.jrs.client.junit;

import java.util.List;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import org.dieschnittstelle.jee.esa.jrs.IProductCRUDService;

public class ProductCRUDClient {

	private IProductCRUDService serviceProxy;
	
	protected static Logger logger = Logger.getLogger(ProductCRUDClient.class);

	public ProductCRUDClient() throws Exception {


		/*
		 * create a client for the web service using ResteasyClientBuilder and ResteasyWebTarget
		 */
		serviceProxy = null;
	}

	public AbstractProduct createProduct(IndividualisedProductItem prod) {
		AbstractProduct created = serviceProxy.createProduct(prod);
		// as a side-effect we set the id of the created product on the argument before returning
		prod.setId(created.getId());
		return created;
	}

	public List<?> readAllProducts() {
		return serviceProxy.readAllProducts();
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		return serviceProxy.updateProduct(update.getId(),(IndividualisedProductItem)update);
	}

	public boolean deleteProduct(long id) {
		return serviceProxy.deleteProduct(id);
	}

	public AbstractProduct readProduct(long id) {
		return serviceProxy.readProduct(id);
	}

}

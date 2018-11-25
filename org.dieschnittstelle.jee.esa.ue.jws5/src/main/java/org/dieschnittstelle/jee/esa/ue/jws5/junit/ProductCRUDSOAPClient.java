package org.dieschnittstelle.jee.esa.ue.jws5.junit;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.erp.ws.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.ws.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.jws.IProductCRUDService;
import org.dieschnittstelle.jee.esa.jws.ProductCRUDWebService;

import java.util.List;

public class ProductCRUDSOAPClient {

	private IProductCRUDService serviceProxy;

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDSOAPClient.class);

	public ProductCRUDSOAPClient() throws Exception {


		/*
		 * create a client for the web service using the service class and obtaining the port for the SOAP binding
       * DO NOT FORGET TO SPECIFY THE WSDL URL IN THE pom.xml FILE OF THE JWS PROJECT (search for TODO)
		 */
		ProductCRUDWebService service = new ProductCRUDWebService();
		serviceProxy = service.getProductCRUDPort();
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
		return serviceProxy.updateProduct(update);
	}

	public boolean deleteProduct(long id) {
		return serviceProxy.deleteProduct(id);
	}

	public AbstractProduct readProduct(long id) {
		return serviceProxy.readProduct(id);
	}

}

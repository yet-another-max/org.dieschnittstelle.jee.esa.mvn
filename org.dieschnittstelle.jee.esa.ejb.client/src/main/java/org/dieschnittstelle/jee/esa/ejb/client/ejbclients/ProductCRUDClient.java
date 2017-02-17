package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import org.dieschnittstelle.jee.esa.ejb.client.Constants;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

public class ProductCRUDClient implements ProductCRUDRemote {

	private ProductCRUDRemote ejbProxy;

	public ProductCRUDClient() throws Exception {
		// obtain a proxy specifying the ejb interface and uri
//		this.ejbProxy = EJBProxyFactory.getInstance().getProxy(null,"");
	}

	public AbstractProduct createProduct(AbstractProduct prod) {

		// JPA3: KOMMENTIEREN SIE DIE FOLGENDE ZUWEISUNG VON IDs UND DIE RETURN-ANWEISUNG AUS
		prod.setId(Constants.nextId());
		return prod;

		// JPA3: KOMMENTIEREN SIE DEN FOLGENDEN CODE EIN		
//		AbstractProduct created = ejbProxy.createProduct(prod);
//		// as a side-effect we set the id of the created product on the argument before returning
//		prod.setId(created.getId());
//		return created;
	}

	public List<AbstractProduct> readAllProducts() {
//		return ejbProxy.readAllProducts();
		return null;
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
//		return ejbProxy.updateProduct(update);
		return null;
	}

	public AbstractProduct readProduct(long productID) {
//		return ejbProxy.readProduct(productID);
		return null;
	}

	public boolean deleteProduct(long productID) {
//		return ejbProxy.deleteProduct(productID);
		return false;
	}

}

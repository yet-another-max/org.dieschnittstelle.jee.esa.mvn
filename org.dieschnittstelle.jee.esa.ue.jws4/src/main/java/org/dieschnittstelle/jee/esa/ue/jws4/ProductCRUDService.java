package org.dieschnittstelle.jee.esa.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.Campaign;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.ProductType;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer 
 * die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 * die Sie aus dem ServletContext auslesen koennen
 */

@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", name="IProductCRUDService", serviceName = "ProductCRUDWebService", portName = "ProductCRUDPort")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
public class ProductCRUDService {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDService.class);

	@Resource
	private WebServiceContext wsContext;

	public ProductCRUDService() {
		logger.info("constructor called");
	}

	@PostConstruct
	@WebMethod(exclude = true)
	public void initializeContext(){
		logger.info("@PostConstruct");
	}

	private GenericCRUDExecutor<AbstractProduct> getCRUDExecuter(){
		return (GenericCRUDExecutor<AbstractProduct>) ((ServletContext)wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT)).getAttribute("productCRUD");
	}

	@WebMethod
	public List<AbstractProduct> readAllProducts() {
		return getCRUDExecuter().readAllObjects();
	}

	@WebMethod
	public AbstractProduct createProduct(AbstractProduct product) {
	    return getCRUDExecuter().createObject(product);
	}

	@WebMethod
	public AbstractProduct updateProduct(AbstractProduct update) {
		return getCRUDExecuter().updateObject(update);
	}

	@WebMethod
	public boolean deleteProduct(long id) {
		return getCRUDExecuter().deleteObject(id);
	}

	@WebMethod
	public IndividualisedProductItem readProduct(long id) {
		return (IndividualisedProductItem) getCRUDExecuter().readObject(id);
	}

}

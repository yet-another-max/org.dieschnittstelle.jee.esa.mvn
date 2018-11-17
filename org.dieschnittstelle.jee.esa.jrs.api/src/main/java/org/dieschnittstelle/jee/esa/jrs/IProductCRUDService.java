package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

/*
 * UE JRS2: 
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

/*
 * UE JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct
 */
@Path("/products")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface IProductCRUDService {

	@POST
	public IndividualisedProductItem createProduct(IndividualisedProductItem prod);

	@GET
	public List<IndividualisedProductItem> readAllProducts();

	@PUT
	@Path("/{productId}")
	public IndividualisedProductItem updateProduct(@PathParam("productId")long id,
												   IndividualisedProductItem update);

	@DELETE
	@Path("/{productId}")
	boolean deleteProduct(@PathParam("productId")long id);

	@GET
	@Path("/{productId}")
	public IndividualisedProductItem readProduct(@PathParam("productId") long id);
			
}

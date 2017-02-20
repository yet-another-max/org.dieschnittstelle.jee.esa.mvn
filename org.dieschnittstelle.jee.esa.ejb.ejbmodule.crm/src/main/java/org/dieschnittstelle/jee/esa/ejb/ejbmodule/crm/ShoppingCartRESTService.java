package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingCartStateful;
import org.dieschnittstelle.jee.esa.entities.crm.CrmProductBundle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by master on 20.02.17.
 */
@Path("/shoppingcarts")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ShoppingCartRESTService {

    @POST
    public long createNewCart();

    @POST
    @Path("/{cartId}")
    public void addProductBundle(@PathParam("cartId") long cartId, CrmProductBundle product);

    @GET
    @Path("/{cartId}")
    public List<CrmProductBundle> getProductBundles(@PathParam("cartId") long cartId);

    @DELETE
    @Path("/{cartId}")
    public boolean deleteCart(@PathParam("cartId") long cartId);

}

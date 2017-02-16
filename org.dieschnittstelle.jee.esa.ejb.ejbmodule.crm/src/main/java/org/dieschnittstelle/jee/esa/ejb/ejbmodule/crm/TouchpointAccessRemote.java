package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import java.util.List;

import javax.ejb.Remote;
//import javax.ejb.Remote;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;

@Remote
@WebService
@Path("/touchpoints")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface TouchpointAccessRemote {

	@POST
	public AbstractTouchpoint createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException;

	@GET
	public List<AbstractTouchpoint> readAllTouchpoints();

}

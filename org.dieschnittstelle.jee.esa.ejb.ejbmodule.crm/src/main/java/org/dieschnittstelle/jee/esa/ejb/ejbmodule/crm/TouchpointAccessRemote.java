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

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;

@Remote
@WebService
@Path("/touchpoints")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface TouchpointAccessRemote {

	@POST
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint touchpoint);

	@GET
	public List<AbstractTouchpoint> readAllTouchpoints();

}

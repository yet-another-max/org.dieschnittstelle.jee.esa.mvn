package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;

@ApplicationPath("/api")
public class RESTWebAPI extends Application {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(RESTWebAPI.class);
	
	public RESTWebAPI() {
		logger.info("<constructor>");
	}
	
}

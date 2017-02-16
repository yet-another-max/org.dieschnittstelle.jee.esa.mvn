package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

@ApplicationPath("/api")
public class RESTWebAPI extends Application {

	protected static Logger logger = Logger.getLogger(RESTWebAPI.class);
	
	public RESTWebAPI() {
		logger.info("<constructor>");
	}
	
}

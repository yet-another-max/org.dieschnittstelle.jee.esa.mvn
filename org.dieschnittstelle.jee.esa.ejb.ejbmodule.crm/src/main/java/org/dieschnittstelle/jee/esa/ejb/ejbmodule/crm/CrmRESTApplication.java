package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

@ApplicationPath("/rest")
public class CrmRESTApplication extends Application {

	protected static Logger logger = Logger.getLogger(CrmRESTApplication.class);
	
	public CrmRESTApplication() {
		logger.info("<constructor>");
	}
	
}

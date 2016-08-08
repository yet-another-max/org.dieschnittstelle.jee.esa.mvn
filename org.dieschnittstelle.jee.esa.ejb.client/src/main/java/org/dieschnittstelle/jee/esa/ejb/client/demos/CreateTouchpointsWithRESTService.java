package org.dieschnittstelle.jee.esa.ejb.client.demos;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.crm.Address;
import org.dieschnittstelle.jee.esa.entities.crm.StationaryTouchpoint;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import org.dieschnittstelle.jee.esa.ejb.client.restclient.ITouchpointAccessRESTService;

public class CreateTouchpointsWithRESTService {

	protected static Logger logger = Logger
			.getLogger(CreateTouchpointsWithRESTService.class);

	public static void main(String[] args) {

		/*
		 * create a client for the web service passing the interface
		 * 
		 * notice that our java interface is located in a different package and
		 * uses different names compared to the server-side jax-rs annotated
		 * interface, but generated the correct http requests to access the
		 * latter's methods.
		 * 
		 * due to the fact that the interfaces' method signatures use abstract
		 * classes, the classes of the objects being passed between client and
		 * service must be identically named on the client side. Note, however,
		 * that the transient attributes that are used on the server side are
		 * not declared on the client's classes
		 */
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://localhost:8080/org.dieschnittstelle.jee.esa.ejb.webapp/rest/");

		ITouchpointAccessRESTService serviceClient = target.proxy(ITouchpointAccessRESTService.class);

		logger.info("created client: " + serviceClient);

		// create the touchpoint
		Address addr1 = new Address();
		addr1.setStreet("Muellerstrasse");
		addr1.setHouseNr("147");
		addr1.setZipCode("13353");
		addr1.setCity("Berlin");
		StationaryTouchpoint tp = new StationaryTouchpoint();
		tp.setName("Rathaus Wedding");
		tp.setLocation(addr1);

		// invoke the creation method
		tp = (StationaryTouchpoint) serviceClient.createNewTouchpoint(tp);

		logger.info("created touchpoint: " + tp);

		// create the touchpoint
		Address addr2 = new Address();
		addr2.setStreet("Luxemburger Strasse");
		addr2.setHouseNr("10");
		addr2.setZipCode("13353");
		addr2.setCity("Berlin");
		StationaryTouchpoint tp2 = new StationaryTouchpoint();
		tp2.setName("BHT Mensa");
		tp2.setLocation(addr1);

		tp2 = (StationaryTouchpoint) serviceClient.createNewTouchpoint(tp2);

		logger.info("created touchpoint: " + tp2);
		
		System.err.println("CreateTouchpointsWithRESTService: done.\n");
	}

}

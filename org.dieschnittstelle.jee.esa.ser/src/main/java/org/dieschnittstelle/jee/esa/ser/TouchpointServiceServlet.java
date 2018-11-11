package org.dieschnittstelle.jee.esa.ser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.dieschnittstelle.jee.esa.utils.Utils.*;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;

public class TouchpointServiceServlet extends HttpServlet {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointServiceServlet.class);

	public TouchpointServiceServlet() {
		show("TouchpointServiceServlet: constructor invoked\n");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("doGet()");

		// we assume here that GET will only be used to return the list of all
		// touchpoints

		// obtain the executor for reading out the touchpoints
		TouchpointCRUDExecutor exec = (TouchpointCRUDExecutor) getServletContext()
				.getAttribute("touchpointCRUD");
		try {
			// set the status
			response.setStatus(HttpServletResponse.SC_OK);
			// obtain the output stream from the response and write the list of
			// touchpoints into the stream
			ObjectOutputStream oos = new ObjectOutputStream(
					response.getOutputStream());
			// write the object
			oos.writeObject(exec.readAllTouchpoints());
			oos.close();
		} catch (Exception e) {
			String err = "got exception: " + e;
			logger.error(err, e);
			throw new RuntimeException(e);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {

		// assume POST will only be used for touchpoint creation, i.e. there is
		// no need to check the uri that has been used

		// obtain the executor for reading out the touchpoints from the servlet context using the touchpointCRUD attribute

		TouchpointCRUDExecutor executor = (TouchpointCRUDExecutor) getServletContext().getAttribute("touchpointCRUD");

		try {
			// create an ObjectInputStream from the request's input stream
			ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
		
			// read an AbstractTouchpoint object from the stream
			AbstractTouchpoint tp = (AbstractTouchpoint)ois.readObject();
		
			// call the create method on the executor and take its return value
			AbstractTouchpoint result = executor.createTouchpoint(tp);
		
			// set the response status as successful, using the appropriate
			// constant from HttpServletResponse
			response.setStatus(HttpStatus.SC_OK);
		
			// then write the object to the response's output stream, using a
			// wrapping ObjectOutputStream
			ServletOutputStream sos = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(sos);

		
			// ... and write the object to the stream
			oos.writeObject(result);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response){
	    TouchpointCRUDExecutor executor = (TouchpointCRUDExecutor) getServletContext().getAttribute("touchpointCRUD");
	    try{

	    	String idString = request.getPathInfo().replace("/", "");

	        long id = Long.parseLong(idString);

			boolean result = executor.deleteTouchpoint(id);
			if(result)
				response.setStatus(HttpStatus.SC_OK);
			else
				response.setStatus(HttpStatus.SC_NOT_FOUND);

		}catch (Exception e){
	        logger.error(e.getMessage(), e);
	        throw new RuntimeException(e);
		}
	}
}

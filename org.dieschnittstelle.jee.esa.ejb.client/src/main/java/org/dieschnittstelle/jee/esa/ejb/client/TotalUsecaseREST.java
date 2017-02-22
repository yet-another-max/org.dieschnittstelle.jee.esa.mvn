package org.dieschnittstelle.jee.esa.ejb.client;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.*;
import org.dieschnittstelle.jee.esa.ejb.client.shopping.ShoppingBusinessDelegate;
import org.dieschnittstelle.jee.esa.ejb.client.shopping.ShoppingSession;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.entities.crm.CampaignExecution;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;

import java.util.Collection;

import static org.dieschnittstelle.jee.esa.ejb.client.Constants.*;
import static org.dieschnittstelle.jee.esa.utils.Utils.step;

public class TotalUsecaseREST {

	protected static Logger logger = Logger.getLogger(TotalUsecaseREST.class);

	public static void main(String[] args) {
		// here, we will use ejb proxies for accessing the server-side components
		EJBProxyFactory.initialise(WEB_API_BASE_URL,true);

		try {
			(new TotalUsecase()).runAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

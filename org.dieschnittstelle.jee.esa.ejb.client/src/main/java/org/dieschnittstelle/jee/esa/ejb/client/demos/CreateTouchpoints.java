package org.dieschnittstelle.jee.esa.ejb.client.demos;

import org.dieschnittstelle.jee.esa.ejb.client.TotalUsecase;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.EJBProxyFactory;

import static org.dieschnittstelle.jee.esa.ejb.client.Constants.WEB_API_BASE_URL;

public class CreateTouchpoints {

	public static void main(String[] args) {
		EJBProxyFactory.initialise();

		try {
			TotalUsecase uc = new TotalUsecase();
			uc.createTouchpoints();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

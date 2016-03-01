package org.dieschnittstelle.jee.esa.ejb.client.demos;

import org.dieschnittstelle.jee.esa.ejb.client.TotalUsecase;

public class PrepareCampaigns {

	public static void main(String[] args) {
		
		TotalUsecase uc;
		try {
			uc = new TotalUsecase();
			uc.prepareCampaigns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

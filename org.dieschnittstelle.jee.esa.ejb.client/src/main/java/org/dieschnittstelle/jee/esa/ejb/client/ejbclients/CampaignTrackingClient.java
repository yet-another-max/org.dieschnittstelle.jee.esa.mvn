package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.CampaignTrackingRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.CampaignExecution;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CampaignTrackingClient implements CampaignTrackingRemote {

	private CampaignTrackingRemote proxy;
	
	public CampaignTrackingClient() throws Exception {
		Context context = new InitialContext();
		proxy = (CampaignTrackingRemote) context
				.lookup(Constants.CAMPAIGN_TRACKING_BEAN);
	}
	
	@Override
	public void addCampaignExecution(CampaignExecution campaign) {
		proxy.addCampaignExecution(campaign);
	}

	@Override
	public int existsValidCampaignExecutionAtTouchpoint(long erpProductId,
			AbstractTouchpoint tp) {
		return proxy.existsValidCampaignExecutionAtTouchpoint(erpProductId, tp);
	}

	@Override
	public void purchaseCampaignAtTouchpoint(long erpProductId,
			AbstractTouchpoint tp, int units) {
		proxy.purchaseCampaignAtTouchpoint(erpProductId, tp, units);
	}

	@Override
	public List<CampaignExecution> getAllCampaignExecutions() {
		return proxy.getAllCampaignExecutions();
	}

}

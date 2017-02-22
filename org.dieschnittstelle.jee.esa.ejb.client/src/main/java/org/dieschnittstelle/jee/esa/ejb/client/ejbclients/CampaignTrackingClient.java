package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.CampaignTrackingRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.CampaignExecution;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CampaignTrackingClient implements CampaignTrackingRemote {

	private CampaignTrackingRemote ejbProxy;
	
	public CampaignTrackingClient() throws Exception {
		ejbProxy = EJBProxyFactory.getInstance().getProxy(CampaignTrackingRemote.class,Constants.CAMPAIGN_TRACKING_BEAN_URI);
	}
	
	@Override
	public void addCampaignExecution(CampaignExecution campaign) {
		ejbProxy.addCampaignExecution(campaign);
	}

	@Override
	public int existsValidCampaignExecutionAtTouchpoint(long erpProductId,
			AbstractTouchpoint tp) {
		return ejbProxy.existsValidCampaignExecutionAtTouchpoint(erpProductId, tp);
	}

	@Override
	public void purchaseCampaignAtTouchpoint(long erpProductId,
			AbstractTouchpoint tp, int units) {
		ejbProxy.purchaseCampaignAtTouchpoint(erpProductId, tp, units);
	}

	@Override
	public List<CampaignExecution> getAllCampaignExecutions() {
		return ejbProxy.getAllCampaignExecutions();
	}

}

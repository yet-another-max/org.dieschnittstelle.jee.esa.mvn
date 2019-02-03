package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.CampaignExecution;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CampaignTrackingLocal {
    public void addCampaignExecution(CampaignExecution campaign);
    public int existsValidCampaignExecutionAtTouchpoint(long erpProductID, AbstractTouchpoint tp);
    public void purchaseCampaignAtTouchpoint(long erpProductID, AbstractTouchpoint tp, int units);
    public List<CampaignExecution> getAllCampaignExecutions();
}

package org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface TouchpointAccessRemoteAsync {

    public Future<AbstractTouchpoint> createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException;

    public Future<List<AbstractTouchpoint>> readAllTouchpoints();

}

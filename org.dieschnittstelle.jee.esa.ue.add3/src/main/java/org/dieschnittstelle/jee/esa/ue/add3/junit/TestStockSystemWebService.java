package org.dieschnittstelle.jee.esa.ue.add3.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

// TODO: generate classes given the wsdl
// TODO: remove the comments
// TODO: import all required classes from the generated packages

/*
 * 
 */
public class TestStockSystemWebService {
	
//	private StockSystemRemote serviceClient;
//
//
//	@Before
//	public void prepareContext() {
//		// TODO instantiate the serviceClient by instantiating the generated web service class and getting the port for StockSystemRemote
//		serviceClient = null;
//	}
//
//	@Test
//	public void stockSystemServiceWorks() {
//		// read out all products
//		List<IndividualisedProductItem> products = serviceClient.getAllProductsOnStock();
//		assertTrue("stock exists and can be read", products.size() > 0);
//
//		// we are using the first product for the tests...
//		IndividualisedProductItem testprod = products.get(0);
//
//		// obtain the poss where the first product is available
//		List<Integer> poss = serviceClient.getPointsOfSale(testprod);
//		assertTrue("selected product is associated with at least one point of sale", poss.size() > 0);
//
//		// we are using the first pos for the tests...
//		int testpos = poss.get(0);
//
//		// obtain the local and total units
//		int unitsAtPos = serviceClient.getUnitsOnStock(testprod, testpos);
//		int unitsTotal = serviceClient.getTotalUnitsOnStock(testprod);
//
//		// add units for the first pos
//		int unitsToAdd = 5;
//		serviceClient.addToStock(testprod, testpos, unitsToAdd);
//
//		// compare the final units
//		assertEquals("after adding units, units at pos correctly incremented", unitsAtPos + unitsToAdd, serviceClient.getUnitsOnStock(testprod, testpos));
//		assertEquals("after adding units, total units correctly incremented", unitsTotal + unitsToAdd, serviceClient.getTotalUnitsOnStock(testprod));
//
//
//	}
	
}

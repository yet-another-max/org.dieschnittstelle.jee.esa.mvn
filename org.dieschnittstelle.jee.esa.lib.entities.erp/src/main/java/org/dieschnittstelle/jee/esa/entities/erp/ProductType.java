package org.dieschnittstelle.jee.esa.entities.erp;

import com.fasterxml.jackson.annotation.JsonCreator;
//import com.sun.xml.internal.txw2.annotation.XmlNamespace;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

//@XmlAccessorType(XmlAccessType.PROPERTY)
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://dieschnittstelle.org/jee/esa/entities/erp/ws")
public enum ProductType {

	BREAD, ROLL, PASTRY;
	
	@JsonCreator
	public static ProductType deserialise(String pt) {	
		return ProductType.valueOf(ProductType.class,pt);
	}
}

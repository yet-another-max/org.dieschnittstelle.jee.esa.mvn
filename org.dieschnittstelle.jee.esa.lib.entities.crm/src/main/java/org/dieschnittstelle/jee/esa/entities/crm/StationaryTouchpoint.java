package org.dieschnittstelle.jee.esa.entities.crm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author kreutel
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(namespace = "http://dieschnittstelle.org/jee/esa/crm/entities")
@Entity
@DiscriminatorValue("stationary")
public class StationaryTouchpoint extends AbstractTouchpoint  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6123798695442913993L;
	
	/**
	 * we assume a onetoone assoc
	 */
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Address location;
	
	public StationaryTouchpoint() {
		System.out.println("<constructor>");
	}
	
	public StationaryTouchpoint(int erpPointOfSaleId) {
		this.setErpPointOfSaleId(erpPointOfSaleId);
	}

	public StationaryTouchpoint(int erpPointOfSaleId,String name,Address address) {
		super.setErpPointOfSaleId(erpPointOfSaleId);
		super.setName(name);
		this.setLocation(address);
	}
	
	public String toString() {
		return "{StationaryTouchpoint " + this.id + "/" + this.erpPointOfSaleId + " " + this.name + " " + this.location + "}";
	}
	
	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}
	
	/*
	 * lifecycle logging
	 */
	
	@PostLoad
	public void onPostLoad() {
		System.out.println("@PostLoad: " + this);
	}
	
	@PostPersist
	public void onPostPersist() {
		System.out.println("@PostPersist: " + this);		
	}
	
	@PostRemove
	public void onPostRemove() {
		System.out.println("@PostRemove: " + this);
	}

	@PostUpdate
	public void onPostUpdate() {
		System.out.println("@PostUpdate: " + this);
	}
	
	@PrePersist
	public void onPrePersist() {
		System.out.println("@PrePersist: " + this);
	}

	@PreRemove
	public void onPreRemove() {
		System.out.println("@PreRemove: " + this);
	}

	@PreUpdate
	public void onPreUpdate() {
		System.out.println("@PreUpdate: " + this);		
	}
	
}

package org.dieschnittstelle.jee.esa.entities.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Location implements Serializable {

	protected static Logger logger = Logger.getLogger(Location.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -131090102062445239L;

	private long id;
	
	private long geoLat;
	
	private long geoLong;

	public Location() {
		logger.info("<constructor>");
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(long geoLat) {
		this.geoLat = geoLat;
	}

	public long getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(long geoLong) {
		this.geoLong = geoLong;
	}
	
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
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

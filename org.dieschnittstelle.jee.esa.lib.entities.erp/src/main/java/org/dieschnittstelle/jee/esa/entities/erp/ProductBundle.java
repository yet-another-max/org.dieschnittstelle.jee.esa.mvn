package org.dieschnittstelle.jee.esa.entities.erp;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

public class ProductBundle implements Serializable {

	protected static Logger logger = Logger.getLogger(ProductBundle.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501911067906145681L;

	private long id;

	private IndividualisedProductItem product;

	private int units;

	public ProductBundle() {
		logger.info("<constructor>");
	}

	public ProductBundle(IndividualisedProductItem product, int units) {
		this.product = product;
		this.units = units;
	}

	public IndividualisedProductItem getProduct() {
		return this.product;
	}

	public void setProduct(IndividualisedProductItem product) {
		this.product = product;
	}

	public int getUnits() {
		return this.units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String toString() {
		return "{" + this.product + " (" + this.units + ")}";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

}

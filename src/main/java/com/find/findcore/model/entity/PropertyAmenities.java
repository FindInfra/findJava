package com.find.findcore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.find.findcore.model.enumeration.EPropertyAmenities;

@Entity
@Table(name = "property_amenities")
public class PropertyAmenities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private EPropertyAmenities propertyAmenities;

	public PropertyAmenities() {
	}

	public PropertyAmenities(EPropertyAmenities propertyAmenities) {
		this.propertyAmenities = propertyAmenities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EPropertyAmenities getPropertyAmenities() {
		return propertyAmenities;
	}

	public void setPropertyAmenities(EPropertyAmenities propertyAmenities) {
		this.propertyAmenities = propertyAmenities;
	}

}

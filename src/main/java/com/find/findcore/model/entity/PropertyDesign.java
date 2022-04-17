package com.find.findcore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.find.findcore.model.enumeration.EPropertyDesign;

@Entity
@Table(name = "property_design")
public class PropertyDesign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private EPropertyDesign propertyDesign;

	public PropertyDesign() {
	}

	public PropertyDesign(EPropertyDesign propertyDesign) {
		this.propertyDesign = propertyDesign;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EPropertyDesign getPropertyDesign() {
		return propertyDesign;
	}

	public void setPropertyDesign(EPropertyDesign propertyDesign) {
		this.propertyDesign = propertyDesign;
	}

	public void setePropertyDesign(EPropertyDesign propertyDesign) {
		this.propertyDesign = propertyDesign;
	}

}

package com.find.findcore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.find.findcore.model.enumeration.EPropertyViews;

@Entity
@Table(name = "property_views")
public class PropertyViews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private EPropertyViews propertyViews;

	public PropertyViews() {
	}

	public PropertyViews(EPropertyViews propertyViews) {
		this.propertyViews = propertyViews;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EPropertyViews getPropertyViews() {
		return propertyViews;
	}

	public void setPropertyViews(EPropertyViews propertyViews) {
		this.propertyViews = propertyViews;
	}

	@Override
	public String toString() {
		return "PropertyViews [id=" + id + ", propertyViews=" + propertyViews + "]";
	}

}

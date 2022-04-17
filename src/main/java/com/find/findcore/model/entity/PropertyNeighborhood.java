package com.find.findcore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.find.findcore.model.enumeration.EPropertyNeighborhood;

@Entity
@Table(name = "property_neighborhood")
public class PropertyNeighborhood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private EPropertyNeighborhood propertyNeighborhood;

	public PropertyNeighborhood() {
	}

	public PropertyNeighborhood(EPropertyNeighborhood propertyNeighborhood) {
		this.propertyNeighborhood = propertyNeighborhood;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EPropertyNeighborhood getPropertyNeighborhood() {
		return propertyNeighborhood;
	}

	public void setPropertyNeighborhood(EPropertyNeighborhood propertyNeighborhood) {
		this.propertyNeighborhood = propertyNeighborhood;
	}

}

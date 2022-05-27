package com.find.findcore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.find.findcore.model.enumeration.EPropertyDistricts;

@Entity
@Table(name = "property_district")
public class PropertyDistrict {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private EPropertyDistricts ePropertyDistricts;

	public PropertyDistrict() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EPropertyDistricts getePropertyDistricts() {
		return ePropertyDistricts;
	}

	public void setePropertyDistricts(EPropertyDistricts ePropertyDistricts) {
		this.ePropertyDistricts = ePropertyDistricts;
	}

}

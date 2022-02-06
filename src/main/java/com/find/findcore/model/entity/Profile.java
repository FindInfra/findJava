package com.find.findcore.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.find.findcore.model.enumeration.Provider;

@Entity
@Table(name = "profile")
/*
 * @Data
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @Builder
 */
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	String fullName;
	@NotBlank
	@Size(max = 120)
	String email;
	@Size(max = 120)
	String displayEmail;
	String phone;
	@Size(max = 15)
	String gender;
	String dateOfBirth;
	Provider provider;
	String avatarImage;
	@CreationTimestamp
	@Column(updatable = false)
	LocalDateTime dateCreated;
	@UpdateTimestamp
	LocalDateTime lastModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profile() {
	}

	public Profile(Integer id, String fullName, @NotBlank @Size(max = 120) String email,
			@Size(max = 120) String displayEmail, String phone, @Size(max = 15) String gender, String dateOfBirth,
			Provider provider, String avatarImage, LocalDateTime dateCreated, LocalDateTime lastModified) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.displayEmail = displayEmail;
		this.phone = phone;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.provider = provider;
		this.avatarImage = avatarImage;
		this.dateCreated = dateCreated;
		this.lastModified = lastModified;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayEmail() {
		return displayEmail;
	}

	public void setDisplayEmail(String displayEmail) {
		this.displayEmail = displayEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

}
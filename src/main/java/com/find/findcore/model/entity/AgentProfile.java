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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agent_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	String fullName;
	@NotBlank
	@Size(max = 15)
	String mobileno;
	@Size(max = 15)
	String licenseno;
	String avatarImage;
	String videoUrl;
	@CreationTimestamp
	@Column(updatable = false)
	LocalDateTime dateCreated;
	@UpdateTimestamp
	LocalDateTime lastModified;
}

package com.amolrang.modume.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Social_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SOCIAL_JPA_SEQ;
	
	@ManyToOne
	@JoinColumn(name = "MAIN_JPA_SEQ")
	private User_JPA MAIN_JPA_SEQ;
	private String social_id;
	private String social_password;
	private String sns;
}

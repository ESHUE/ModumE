package com.amolrang.modume.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Authorize_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int AUTH_JPA_SEQ;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User_JPA username;
	
	private String Authentication;
}

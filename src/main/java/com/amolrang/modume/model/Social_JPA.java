
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
	private int socialSeq;
	
	@ManyToOne
	@JoinColumn(name = "userSeq")
	private User_JPA userSeq;
	private String socialUsername;
	private String username;
	private String sns;
}

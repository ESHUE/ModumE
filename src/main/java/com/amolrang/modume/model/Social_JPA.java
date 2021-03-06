
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
	private int socialseq;
	
	@ManyToOne
	@JoinColumn(name = "userseq")
	private User_JPA userseq;
	private String socialusername;
	private String username;
	private String sns;
	private String token;
	private String clientid;
}

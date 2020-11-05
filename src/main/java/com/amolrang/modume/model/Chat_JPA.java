
package com.amolrang.modume.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Chat_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chatseq;
	private String streamerId;
	private String userName;
	private String ctnt;
	private LocalDateTime r_dt;
	public Chat_JPA() {
		r_dt = LocalDateTime.now();
	}

}
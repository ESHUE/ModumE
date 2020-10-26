package com.amolrang.modume.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Userboard_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int USERBOARD_SEQ;
	@Column(length = 100)
	private String title;
	@Column(length = 10000)
	private String content;
	private int hits;
	@CreationTimestamp
	private Timestamp r_date;
	private Timestamp m_date;
	@ManyToOne
	@JoinColumn(name = "MAIN_SEQ")
	private User_JPA MAIN_SEQ;
	
	@Transient
	private String username;
	@Transient
	private String profileImg;
}

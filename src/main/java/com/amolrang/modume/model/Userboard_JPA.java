package com.amolrang.modume.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Userboard_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int boardseq;
	@Column(length = 128)
	private String title;
	@Column(length = 5000)
	private String content;
	@Column(length = 5000)
	private String convertcontent;
	@ColumnDefault("0")
	private int hits;
	@CreationTimestamp
	private Timestamp rdate;
	@CreationTimestamp
	private Timestamp mdate;
	@Column
	private boolean quicklink;
	@Column(length = 128)
	private String quicklinkurl;
	
	@ManyToOne
	@JoinColumn(name = "userseq")
	private User_JPA userseq;
	
}
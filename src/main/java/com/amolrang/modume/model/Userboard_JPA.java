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
	private int boardseq;
	@Column(length = 100)
	private String title;
	@Column(length = 10000)
	private String content;
	@ColumnDefault("0")
	private int hits;
	@CreationTimestamp
	private Timestamp rdate;
	@CreationTimestamp
	private Timestamp mdate;
	@ManyToOne
	@JoinColumn(name = "userseq")
	private User_JPA userseq;
}
package com.amolrang.modume.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
//@IdClass(CommentId_JPA.class)
public class Comment_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentseq;
	
	@Column
	private String commentcontent;
	@CreationTimestamp
	private Timestamp rdate;
	@ManyToOne
	@JoinColumn(name = "userseq")
	private User_JPA userseq;
	@ManyToOne
	@JoinColumn(name = "boardseq")
	private Userboard_JPA boardseq;
	
}

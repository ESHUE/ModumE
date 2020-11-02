package com.amolrang.modume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;


@Entity
@Data
@IdClass(BoardimgId_JPA.class)
public class Boardimg_JPA {
	/*
	 * CREATE TABLE img_jpa (
	boardseq INT(11),
	imgseq INT(11),  - pk지만 auto_increment 아님, service에서 처리
	imgname VARCHAR(255) NOT NULL,
	imgpath VARCHAR(255) NOT NULL,
	FOREIGN KEY (boardseq) REFERENCES userboard_jpa(boardseq),
	PRIMARY KEY (boardseq, imgseq)
);
	 */
	
	@Id
	@Column
	private int imgseq;
	@Column(nullable = false)
	private String imgpath;
	@Id
	@ManyToOne
	@JoinColumn(name = "boardseq")
	private Userboard_JPA boardseq;
	

}

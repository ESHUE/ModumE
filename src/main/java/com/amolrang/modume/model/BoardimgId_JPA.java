package com.amolrang.modume.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BoardimgId_JPA implements Serializable{
	private static final long serialVersionUID = 1L;
	private int imgseq;
	private Userboard_JPA boardseq;
	
	public BoardimgId_JPA() {}
	
	public BoardimgId_JPA(int imgseq, Userboard_JPA boardseq) {
		this.imgseq = imgseq;
		this.boardseq = boardseq;
	}
}

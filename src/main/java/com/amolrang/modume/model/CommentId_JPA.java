package com.amolrang.modume.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentId_JPA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int commentseq;
	private Userboard_JPA boardseq;
	
}

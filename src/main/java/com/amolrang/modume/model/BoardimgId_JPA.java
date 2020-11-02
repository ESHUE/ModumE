package com.amolrang.modume.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BoardimgId_JPA implements Serializable{
	private int imgseq;
	private Userboard_JPA boardseq;
}

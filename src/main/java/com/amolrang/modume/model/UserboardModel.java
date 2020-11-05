package com.amolrang.modume.model;

import java.util.List;

import lombok.Data;

@Data
public class UserboardModel extends Userboard_JPA {
	private List<Boardimg_JPA> imgList;
	private int comment_cnt;
}

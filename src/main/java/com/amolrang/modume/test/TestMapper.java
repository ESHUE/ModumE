package com.amolrang.modume.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.amolrang.modume.model.Boardimg_JPA;

@Mapper
public interface TestMapper {
	@Insert("INSERT INTO boardimg_jpa(imgseq, boardseq, imgpath) VALUES (#{imgseq}, #{boardseq.boardseq}, #{imgpath})")
	void insBoardimg(Boardimg_JPA entity);
}
package com.amolrang.modume.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.Userboard_JPA;

@Mapper
public interface TestMapper {
	@Insert("INSERT INTO boardimg_jpa(imgseq, boardseq, imgpath) VALUES (#{imgseq}, #{boardseq.boardseq}, #{imgpath})")
	void insBoardimg(Boardimg_JPA boardImg_jpa);
	
	@Update("UPDATE userboard_jpa SET content = #{content}, convertcontent = #{convertcontent}, mdate = NOW(), title = #{title} WHERE boardseq = #{boardseq}")
	int updUserBoard(Userboard_JPA userBoard_jpa);
}
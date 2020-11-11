package com.amolrang.modume.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.Comment_JPA;
import com.amolrang.modume.model.Userboard_JPA;

@Mapper
public interface TestMapper {
	@Insert("INSERT INTO boardimg_jpa(imgseq, boardseq, imgpath) VALUES (#{imgseq}, #{boardseq.boardseq}, #{imgpath})")
	void insBoardimg(Boardimg_JPA boardImg_jpa);
	
	@Update("UPDATE userboard_jpa SET content = #{content}, convertcontent = #{convertcontent}, mdate = NOW(), title = #{title} WHERE boardseq = #{boardseq}")
	int updUserBoard(Userboard_JPA userBoard_jpa);

	@Update("UPDATE comment_jpa SET commentcontent = #{commentcontent} WHERE commentseq = #{commentseq}")
	void updComment(Comment_JPA comment_jpa);
	
	@Select("SELECT count(*) FROM comment_jpa WHERE boardseq = #{boardseq}")
	int selCountComment(Userboard_JPA userBoard_jpa);
	
	/*
	@Insert("INSERT INTO comment_jpa (commentcontent, boardseq, userseq) VALUES (#{commentcontent}, #{boardseq.boardseq}, #{userseq.userseq})")
	void insComment(Comment_JPA comment_jpa);
	*/
}
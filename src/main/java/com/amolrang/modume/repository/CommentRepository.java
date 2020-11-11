package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.amolrang.modume.model.Comment_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;

public interface CommentRepository extends JpaRepository<Comment_JPA, Integer> {

	Comment_JPA findByCommentseq(int commentseq);
	
	@Transactional 
	int deleteByCommentseq(int commentseq);
	@Transactional 
	int deleteByBoardseq(Userboard_JPA userboard_jpa);

	List<Comment_JPA> findByBoardseq(Userboard_JPA userboard_jpa);

	List<Comment_JPA> findByBoardseqOrderByCommentseqDesc(Userboard_JPA userboard_jpa);

	List<Comment_JPA> findByUserseqAndBoardseq(User_JPA user_jpa, Userboard_JPA userboard_jpa);

	List<Comment_JPA> findByUserseqAndBoardseqOrderByCommentseqDesc(User_JPA user_jpa, Userboard_JPA userboard_jpa);

}

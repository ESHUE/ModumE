package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;


public interface UserBoardRepository extends JpaRepository<Userboard_JPA, Integer>{
	@Query(value = "SELECT A.boardseq, A.title, A.content, A.hits, DATE_FORMAT(A.rdate, '%m-%d') as rdate, DATE_FORMAT(A.mdate, '%m-%d') as madate, A.convertcontent, B.username, B.profileImg FROM Userboard_JPA A JOIN User_JPA B ON A.userseq = B.userseq ORDER BY A.boardseq DESC")
	List<Userboard_JPA> selAllByUserseq();
	List<Userboard_JPA> findAllByOrderByBoardseqDesc();
	Userboard_JPA findByBoardseq(int boardseq);
	
	@Query(value = "SELECT MAX(A.boardseq) FROM Userboard_JPA A WHERE A.userseq = ?1")
	int findMaxBoardseqByUserSeq(User_JPA userseq);
	@Transactional 
	void deleteByBoardseq(int boardseq);
}

package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Userboard_JPA;


public interface UserBoardRepository extends JpaRepository<Userboard_JPA, Integer>{
	@Query(value = "SELECT A.boardseq, A.title, A.content, A.hits, DATE_FORMAT(A.rdate, '%m-%d'), B.username, B.profileImg FROM Userboard_JPA A JOIN User_JPA B ON A.userseq = B.userseq")
	List<Userboard_JPA> selAllByUserseq();
	List<Userboard_JPA> findAllByOrderByBoardseqDesc();
	Userboard_JPA findByBoardseq(int boardseq);
}

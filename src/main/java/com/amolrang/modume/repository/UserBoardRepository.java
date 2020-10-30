package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Userboard_JPA;


public interface UserBoardRepository extends JpaRepository<Userboard_JPA, Integer>{
	@Query(value = "SELECT A.boardSeq, A.title, A.content, A.hits, DATE_FORMAT(A.rDate, '%m-%d') AS rDate, B.username, B.profileImg FROM Userboard_JPA A JOIN User_JPA B ON A.userSeq = B.userSeq")
	List<Userboard_JPA> findAllBoardList();
	Userboard_JPA findByBoardSeq(int boardSeq);
}

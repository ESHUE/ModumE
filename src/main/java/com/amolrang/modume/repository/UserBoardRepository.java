package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Userboard_JPA;


public interface UserBoardRepository extends JpaRepository<Userboard_JPA, Integer>{
	@Query(value = "SELECT A.USERBOARD_SEQ, A.title, A.content, A.hits, DATE_FORMAT(A.r_date, '%m-%d') AS r_date, B.username, B.profileImg FROM Userboard_JPA A JOIN User_JPA B ON A.user = B.MAIN_SEQ")
	List<Userboard_JPA> findBoardWithMAIN_SEQ();
}

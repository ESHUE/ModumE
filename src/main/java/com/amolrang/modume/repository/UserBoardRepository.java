package com.amolrang.modume.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.UserBoard_JPA;

public interface UserBoardRepository extends JpaRepository<UserBoard_JPA, Integer>{
	//@Select("A.userboard_seq, A.title, A.content, A.hits, DATE_FORMAT(A.r_date, '%m-%d') AS r_date, B.username, B.profileimg FROM userboard_jpa A JOIN user_jpa B ON A.main_seq = B.main_seq")
	//List<UserBoard_JPA> findBoardWithMain_seq();
}

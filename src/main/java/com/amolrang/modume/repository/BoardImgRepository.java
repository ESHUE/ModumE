package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.Userboard_JPA;

public interface BoardImgRepository extends JpaRepository<Boardimg_JPA, Integer> {

	List<Boardimg_JPA> findByBoardseq(Userboard_JPA boardseq);
	@Transactional 
	void deleteByBoardseq(Userboard_JPA boardseq);
}

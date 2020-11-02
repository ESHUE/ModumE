package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.BoardimgId_JPA;
import com.amolrang.modume.model.Boardimg_JPA;

public interface BoardImgRepository extends JpaRepository<Boardimg_JPA, BoardimgId_JPA> {
//	@Query("INSERT INTO Boardimg_JPA(imgseq, boardseq, imgpath) VALUES (:#{#entity.imgseq}, :#{#entity.boardseq.boardseq}, :#{#entity.imgpath})")
//	void insBoardimg(Boardimg_JPA entity);
}
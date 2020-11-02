package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.Userboard_JPA;

public interface BoardImgRepository extends JpaRepository<Boardimg_JPA, Integer> {
}

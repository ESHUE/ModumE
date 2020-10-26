package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.Social_JPA;

public interface SocialRepository extends JpaRepository<Social_JPA, Integer>{
	String findByUsername(String Username);
}
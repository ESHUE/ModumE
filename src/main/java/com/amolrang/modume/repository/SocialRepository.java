package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.User_JPA;

public interface SocialRepository extends JpaRepository<User_JPA, Integer>{
	String findByUsername(String username);
}

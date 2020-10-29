package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.User_JPA;

public interface UserRepository extends JpaRepository<User_JPA, Integer>{
	User_JPA findByUsername(String username);
	User_JPA findByNickname(String nickname);
}

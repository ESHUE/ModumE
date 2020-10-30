package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Authorize_JPA;
import com.amolrang.modume.model.User_JPA;

public interface AuthRepository extends JpaRepository<Authorize_JPA, Integer>{
	@Query(value = "SELECT authentication FROM Authorize_JPA WHERE username = :userModel")
	List<String> SelAllByUsername(User_JPA userModel);
}


package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Authorize_JPA;
public interface AuthRepository extends JpaRepository<Authorize_JPA, Integer>{
	@Query(value = "SELECT authentication FROM Authorize_JPA WHERE username = :username")
	List<String> SelAllByUsername(String username);
}


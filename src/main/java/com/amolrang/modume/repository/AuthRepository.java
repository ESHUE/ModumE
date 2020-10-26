package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amolrang.modume.model.Authorize_JPA;

public interface AuthRepository extends JpaRepository<Authorize_JPA, Integer>{
	@Query(value = "SELECT Authentication FROM Authorize_JPA  WHERE user = :username ")
	List<String> findAuthenticationByUsername(String username);
}


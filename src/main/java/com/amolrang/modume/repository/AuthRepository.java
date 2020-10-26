
package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.Authorize_JPA;

public interface AuthRepository extends JpaRepository<Authorize_JPA, Integer>{
	//List<Authorize_JPA> findByUsername(String username);
}

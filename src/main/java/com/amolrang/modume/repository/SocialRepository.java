
package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;

public interface SocialRepository extends JpaRepository<Social_JPA, Integer>{
	Social_JPA findBySocialUsername(String socialUsername);
	List<Social_JPA> findAllByUser(User_JPA user);
}

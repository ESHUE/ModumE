
package com.amolrang.modume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;

public interface SocialRepository extends JpaRepository<Social_JPA, Integer>{
	Social_JPA findBySocialUsername(String socialUsername);
	List<Social_JPA> findAllByUser(User_JPA user);
	@Modifying
	@Transactional
	@Query(value = "UPDATE Social_JPA SET user = :#{#social.user} WHERE socialUsername = :#{#social.socialUsername}")
	void updateToMainSeq(Social_JPA social);
	User_JPA findByUser(User_JPA user);
}

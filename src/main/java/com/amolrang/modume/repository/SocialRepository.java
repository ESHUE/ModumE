
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
	List<Social_JPA> findAllByUserSeq(User_JPA userSeq);
	@Modifying
	@Transactional
	@Query(value = "UPDATE Social_JPA SET userSeq = :#{#social.userSeq} WHERE socialUsername = :#{#social.socialUsername}")
	void updateToMainSeq(Social_JPA social);
}

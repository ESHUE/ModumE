package com.amolrang.modume.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.amolrang.modume.model.SocialModel;
import com.amolrang.modume.model.TestModel;
import com.amolrang.modume.model.UserModel;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM site_auth inner join user_site on site_auth.seq = user_site.seq WHERE id=#{id}")
	UserModel readUser(String id);
	
	@Select("SELECT * FROM site_auth WHERE id=#{id}")
	SocialModel selUser(String id);
	
//	@Insert("INSERT INTO user_info(id, password, isAccountNonexpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, username)(select #{id}, #{password}, #{isAccountNonExpired}, #{isAccountNonLocked}, #{isCredentialsNonExpired}, #{isEnabled}, #{username})")
//	int insertUser(UserModel userModel);
	
	@Insert("INSERT INTO AUTHORITY VALUES(#{id},#{autority})")
	int insertUserAutority(@Param("id") String id, @Param("autority") String autority);
	
	@Select("SELECT autority FROM AUTHORITY WHERE id=#{id}")
	List<String> readautorities(String id);
	
	
	// 회원정보의 seq를 받아와서 저장
	// 현재 임시적 조치
	@Insert("INSERT INTO user_site(isAccountNonexpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, username)(select #{isAccountNonExpired}, #{isAccountNonLocked}, #{isCredentialsNonExpired}, #{isEnabled}, #{username})")
	@Options(useGeneratedKeys = true, keyProperty = "seq")
	int insUser(UserModel userModel);
	
	@Insert("INSERT INTO site_auth(seq, id, password)values(#{seq}, #{id},#{password})")
	int insertUser(TestModel testModel);

	@Insert("Insert into social(seq,s_id,username,sns)values(#{seq}, #{s_id},#{username},#{sns})")
	int insertSocialUser(SocialModel socialModel);
}

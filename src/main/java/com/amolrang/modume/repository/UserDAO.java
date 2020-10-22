package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amolrang.modume.mapper.UserMapper;
import com.amolrang.modume.model.SocialModel;
import com.amolrang.modume.model.TestModel;
import com.amolrang.modume.model.UserModel;

@Repository
public class UserDAO {
	@Autowired
	UserMapper userMapper;

	public UserModel findById(String id) {
		return userMapper.readUser(id);
	}
	
	
	public UserModel save(UserModel userModel, String role) {
		//userMapper.insertUser(userModel);
		userMapper.insUser(userModel);
		//userMapper.insertUser(userModel);
		userMapper.insertUserAutority(userModel.getId(), role);
		return userModel;
	}

	public List<String> findAuthoritiesByID(String username) {
		return userMapper.readautorities(username);
	}
	
	
	// 추가된 곳
	// Return타입 잘 확인하기( SocialModel임)
	public SocialModel findId(String id) {
		return userMapper.selUser(id);
	}

	public TestModel saveUser(TestModel testModel) {
		userMapper.insertUser(testModel);	
		return testModel;
	}

	public SocialModel socialSave(SocialModel socialModel, String role) {
		// TODO Auto-generated method stub
		userMapper.insertSocialUser(socialModel);
		return socialModel;
	}
}

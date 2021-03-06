package com.amolrang.modume.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amolrang.modume.mapper.UserMapper;
import com.amolrang.modume.model.SocialModel;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.test.TestModel;

@Repository
public class UserDAO {
	@Autowired
	UserMapper userMapper;

	public UserModel findById(String id) {
		return userMapper.readUser(id);
	}
	
	
	public UserModel save(UserModel userModel, String role) {
		userMapper.insUser(userModel);
		userMapper.insertUserAutority(userModel.getUsername(), role);
		return userModel;
	}

	public List<String> findAuthoritiesByID(String username) {
		return userMapper.readautorities(username);
	}
	
	
	// 추가된 곳
	// Return타입 잘 확인하기(SocialModel임)
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
	
	public SocialModel updateSocialSeq(int seq) {
		userMapper.updateSocialSeq(seq);
		return userMapper.findSelUser(seq);
	}
	
	public int findUser(String userName) {
		return userMapper.findUser(userName);
	}
}

package com.amolrang.modume.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amolrang.modume.model.SocialModel;
import com.amolrang.modume.model.TestModel;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.AuthRepository;
import com.amolrang.modume.repository.UserDAO;
import com.amolrang.modume.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserDAO userDAO;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User_JPA userModel = userRepository.findByUsername(username);
		log.info("userServiceModel:{}",userModel);
		
		if(userModel == null) {return userModel;};
		userModel.setAuthorities(getAuthorities(username));
		return userModel;
	}
	
	//현재 마이페이지로 가는 페이지가 없으므로 소셜 로그인시 db에 정보가없으면 자동으로 검사
	public UserDetails loadSocialUserName(String user_id) throws UsernameNotFoundException{
		SocialModel socialModel = userDAO.findId(user_id);
		if(socialModel == null) { return socialModel;};
		return socialModel;
	}
	
	public UserModel save(UserModel userModel, String role) {
		// TODO Auto-generated method stublog.info(role);
		UserModel result = userDAO.findById(userModel.getId());
		if( result != null ){return null;}
		
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		userModel.setAccountNonExpired(true);
		userModel.setAccountNonLocked(true);
		userModel.setCredentialsNonExpired(true);
		userModel.setEnabled(true);
		userModel.setUsername(userModel.getUsername());
		//saveUser(userModel);
		
		return userDAO.save(userModel, role);
	}
	
	//userModel에서 뽑아온 정보에서 id / pw만 추출
	public TestModel saveUser(UserModel userModel) {
		TestModel testModel = new TestModel();
		testModel.setSeq(userModel.getSeq());
		testModel.setId(userModel.getId());
		testModel.setPassword(userModel.getPassword());
		log.info("testModel:{}",testModel);
		return userDAO.saveUser(testModel);
	}

	public Collection<GrantedAuthority> getAuthorities(String id) {
		//log.info(msg);
		User_JPA userModel = userRepository.findByUsername(id);
		
		List<String> string_authorities = userRepository.findByUsername(id);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}

	//소셜 로그인시 
	//사이트 로그인 > 소셜 로그인 : UserService.socialSave > UserDAO.socialSave > UserMapper.insertSocialUser 
	// (추가된 경로) : UserService.findUser > UserService updateSocialSeq > UserDAO.updateSocialSeq > 
	// UserMapper.updateSocialSeq > UserMapper.findSelUser
	//소셜로그인(단독) : UserService.socialSave > UserDAO.socialSave > UserMapper.insertSocialUser
	public SocialModel socialSave(SocialModel socialModel, String role) {
		// TODO Auto-generated method stub
		// userModel에서의 seq를 받아서 넣을 예정
		return userDAO.socialSave(socialModel, role);
	}
	
	public SocialModel updateSocialSeq(int seq) {
		//userModel에서의 seq를 받아서 SocialModel에 업글한다
		return userDAO.updateSocialSeq(seq);
	}
	
	public int findUser(String userName) {
		log.info("test00:{}",userName);
		return userDAO.findUser(userName);
	}

}


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

import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.repository.UserDAO;

@Service
public class UserService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserDAO userDAO;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		log.info(user_id);
		UserModel userModel = userDAO.findById(user_id);
		if(userModel == null) {return userModel;};
		userModel.setAuthorities(getAuthorities(user_id));
		return userModel;
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
		
		log.info("userModel {}",userModel);
		
		return userDAO.save(userModel, role);
	}

	public Collection<GrantedAuthority> getAuthorities(String id) {
		List<String> string_authorities = userDAO.findAuthoritiesByID(id);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}

}


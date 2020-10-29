package com.amolrang.modume.model;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Data
public class UserModel extends User_JPA{
	private Collection<String> sns;
}

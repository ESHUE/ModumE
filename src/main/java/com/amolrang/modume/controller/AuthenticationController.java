package com.amolrang.modume.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amolrang.modume.api.UserModelGetToToken;
import com.amolrang.modume.model.Authorize_JPA;
import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.AuthRepository;
import com.amolrang.modume.repository.SocialRepository;
import com.amolrang.modume.repository.UserRepository;
import com.amolrang.modume.test.TestModel;
import com.amolrang.modume.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthenticationController {
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	SocialRepository socialRepository;
	
	@Autowired
	private UserModelGetToToken callApi;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, Principal principal) {
		log.info("로그인페이지 GET접근 :{}", principal);
		model.addAttribute(StringUtils.TitleKey(), "로그인페이지");
		return StringUtils.LoginURLValue();
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	public String login(Model model, HttpSession hs,Principal principal) {
		log.info("로그인 성공페이지 GET접근 :{}", principal);
		model.addAttribute(StringUtils.TitleKey(), "로그인페이지");
		
		//기존 데이터베이스에 있는 자료 들고오기
		//----------------------------------
		log.info(principal.getName());
		log.info("userInfo:{}",userRepository.findByUsername(principal.getName()));
		User_JPA UserInfoJson = userRepository.findByUsername(principal.getName());
		//----------------------------------
		log.info("UserInfoJson:{}",UserInfoJson);
		
		//User_JPA의 정보를 Session에 넣는다.
		hs.setAttribute("UserInfo", UserInfoJson);
		model.addAttribute("UserInfo", UserInfoJson);
		//seq만 따로 세션에 박는다 ( 추후에 따로 뽑아내기 위해서)
		hs.setAttribute("userModelSeq", UserInfoJson.getMAIN_SEQ());
		return "redirect:/main";
	}

	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login_success(Model model, OAuth2AuthenticationToken authentication, HttpSession hs, Principal principal) {
		log.info("로그인 성공 페이지 GET접근 :{}", principal);
		
		//SocialModel 정보 받아오기 (CallApi으로부터)
		Social_JPA UserInfoJson = callApi.CallUserInfoToJson(authentication, authorizedClientService);
		User_JPA userModel = (User_JPA)hs.getAttribute("UserInfo");
		//세션에서의 정보가 없을경우 소셜사이트 로그인으로 인식 (seq는 가져오지못하므로 기본값 0으로 박힌다)
		if(hs.getAttribute("UserInfo")==null) {
			socialRepository.save(UserInfoJson);
		}
		else {
			//사이트 로그인후 소셜사이트 로그인(seq를 가져올수있음)
			UserInfoJson.setUser(userModel);
			socialRepository.save(UserInfoJson);
			
		}
		log.info("socialModel:{}",UserInfoJson);
		model.addAttribute("UserInfo", UserInfoJson);
		hs.setAttribute("UserInfo", UserInfoJson);
		
		return "redirect:/main";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		log.info("회원가입 get 접근");
		model.addAttribute(StringUtils.TitleKey(), "회원가입페이지");
		model.addAttribute(StringUtils.LoginURLKey(), StringUtils.LoginURLValue());
		return StringUtils.JoinURLValue();
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinAction(Model model, User_JPA userJPA) {
		log.info("회원가입 post 접근");
		//user_JPA 값 넣기
		User_JPA user = new User_JPA();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setNickname(userJPA.getNickname());
		user.setUsername(userJPA.getUsername());
		user.setPassword(passwordEncoder.encode(userJPA.getPassword()));
		userRepository.save(user);
		
		//Authorize_JPA 값 넣기
		Authorize_JPA authorize = new Authorize_JPA();
		authorize.setAUTH_SEQ(user.getMAIN_SEQ());
		authorize.setAuthentication("ROLE_MEMBER");
		authorize.setUser(user);
		authRepository.save(authorize);
		
		log.info("userJPA:{}",user);
		return "redirect:/main";
	}

	@RequestMapping(value = "/denied")
	public String denied(Model model) {
		model.addAttribute(StringUtils.TitleKey(), "거부 당함");
		return StringUtils.Denied();
	}
}

package com.amolrang.modume.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.api.UserModelGetToToken;
import com.amolrang.modume.model.Authorize_JPA;
import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.AuthRepository;
import com.amolrang.modume.repository.SocialRepository;
import com.amolrang.modume.repository.UserRepository;
import com.amolrang.modume.service.UserService;
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
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, Principal principal) {
		log.info("로그인페이지 GET접근 :{}", principal);
		model.addAttribute(StringUtils.TitleKey(), "로그인페이지");
		return StringUtils.LoginURLValue();
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	public String login(HttpSession hs,Principal principal) {
		UserModel userDomain=null;
		//기존 데이터베이스에 있는 자료 들고오기
		//----------------------------------
		log.info(principal.getName());
		log.info("userInfo:{}",userRepository.findByUsername(principal.getName()));
		User_JPA UserInfoJson = userRepository.findByUsername(principal.getName());
		//----------------------------------
		// 가져온 db의 시퀀스 값으로 연동된 sns를 찾아온다.
		List<Social_JPA> social_JPA_List= socialRepository.findAllByUserseq(UserInfoJson);
//		log.info("social_JPA_List:{}",social_JPA_List);
//		System.out.println("social_JPA_List != null ? " + social_JPA_List != null);
		if(social_JPA_List != null) {
			userDomain = new UserModel();
			for(Social_JPA sns : social_JPA_List) {
				userDomain.getSns().add(sns.getSns());
			}
		}
		hs.setAttribute("userDomain", userDomain);
		hs.setAttribute("userInfo", UserInfoJson);
		
		return "redirect:/main";
	}

	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login_success(OAuth2AuthenticationToken authentication, HttpSession hs) {
		UserModel userDomain=null;
		log.info("로그인 성공 페이지 GET접근 :{}", authentication);
		//소셜 로그인시 연동된 계정을 확인하고, 연동된 계정이 있다면 연동된 계정으로 로그인 할 것. (ex -> modume+twitch에서 twitch로 로그인시 modume로 로그인 되게 할것 )
		
		//SocialModel 정보 받아오기 (CallApi으로부터)
		Social_JPA UserInfoJson = callApi.CallUserInfoToJson(authentication, authorizedClientService);
		log.info("123. Social_JPA: {}",UserInfoJson);
		User_JPA loginedUser = (User_JPA)hs.getAttribute("userInfo");
		log.info("132. User_JPA: {}", loginedUser);
		
		// 유저정보 업데이트
		if(loginedUser != null) {
//			System.out.println("두 아이디 연동 시작");
			UserInfoJson.setUserseq(loginedUser);
			if(socialRepository.findBysocialusername(UserInfoJson.getSocialusername()) == null) {
				socialRepository.save(UserInfoJson);
			} else {				
				socialRepository.updateToMainseq(UserInfoJson);
			}
		}else {
			if(socialRepository.findBysocialusername(UserInfoJson.getSocialusername()) == null) {
				socialRepository.save(UserInfoJson);
			}
			UserInfoJson = socialRepository.findBysocialusername(UserInfoJson.getSocialusername());
			log.info("UserInfoJson:{}",UserInfoJson);
			if(UserInfoJson.getUserseq() != null ) {
				loginedUser = UserInfoJson.getUserseq();
			}
			log.info("loginedUser:{}",loginedUser);
		}

		//유저정보 받아오기
		List<Social_JPA> social_JPA_List= socialRepository.findAllByUserseq(loginedUser);
		if(social_JPA_List != null) {
			userDomain = new UserModel();
			userDomain.setUsername(social_JPA_List.get(0).getUsername());
			for(Social_JPA sns : social_JPA_List) {
				System.out.println(sns.getSns());
				userDomain.getSns().add(new String(sns.getSns()));
			}
		}
		hs.setAttribute("userDomain", userDomain);
		hs.setAttribute("userInfo", loginedUser);
		hs.setAttribute("member", userDomain.getUsername());
		
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
	public String joinAction(Model model, User_JPA userJPA, MultipartHttpServletRequest mr) {
		log.info("회원가입 post 접근");
		//user_JPA 값 넣기
		//log.info(userJPA.getProfileImg());
		User_JPA user = new User_JPA();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setNickname(userJPA.getNickname());
		user.setUsername(userJPA.getUsername());
		user.setProfileImg(userJPA.getProfileImg());
		user.setPassword(passwordEncoder.encode(userJPA.getPassword()));
		user.setProfileImg(userService.saveProfileFile(userJPA, mr));
		userRepository.save(user);
		
		//Authorize_JPA 값 넣기
		Authorize_JPA authorize = new Authorize_JPA();
		authorize.setAuthsseq(user.getUserseq());
		authorize.setAuthentication("ROLE_MEMBER");
		authorize.setUsername(user);
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
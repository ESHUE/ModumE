package com.amolrang.modume.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amolrang.modume.api.UserModelGetToToken;
import com.amolrang.modume.model.SocialModel;
import com.amolrang.modume.model.TestModel;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.service.UserService;
import com.amolrang.modume.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@Autowired
	private UserModelGetToToken callApi;

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
		UserModel UserInfoJson = new UserModel();
		UserInfoJson.setUsername(principal.getName());
		hs.setAttribute("userInfo", UserInfoJson);
		return "redirect:/main";
	}

	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login_success(Model model, OAuth2AuthenticationToken authentication, HttpSession hs) {
		log.info("로그인 성공 페이지 GET접근 :{}", authentication);
		model.addAttribute(StringUtils.TitleKey(), "로그인 성공 페이지");
		
		//SocialModel 정보 받아오기 (CallApi으로부터)
		SocialModel UserInfoJson = callApi.CallUserInfoToJson(authentication, authorizedClientService);
		log.info("socialModel:{}",UserInfoJson);
		model.addAttribute("userInfo", UserInfoJson);
		hs.setAttribute("userInfo", UserInfoJson);
		
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
	public String joinAction(Model model, UserModel userModel, TestModel testModel) {
		log.info("회원가입 post 접근");
		if( userService.save(userModel, "ROLE_MEMBER") == null ) {
			return "/joinError";
		}else {
			//UserModel에서 저장된 정보를 site_auth에 저장하기 위해 재진입
			userService.saveUser(userModel);
		}
		return "redirect:/main";
	}

	@RequestMapping(value = "/denied")
	public String denied(Model model) {
		model.addAttribute(StringUtils.TitleKey(), "거부 당함");
		return StringUtils.Denied();
	}
}

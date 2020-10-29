package com.amolrang.modume.test;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RestController
@Controller
public class TestController {
	@Autowired
	private TestService service;

	@RequestMapping(value = "/test", produces = "text/plain;charset=UTF-8")
	public String test(Principal principal, OAuth2AuthenticationToken authentication) {
		log.info("principal:{}", principal);
		return principal.toString();
	}

	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Principal principal) {
		return "/boardList";
	}

	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Principal principal) {
		return "/boardDetail";
	}

	@RequestMapping(value = "/boardRegMod", method = RequestMethod.GET)
	public String boardRegMod(Principal principal) {
		log.info("principal:{}", principal);
		return "/boardRegMod";
	}
	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public String imageUpload(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest multiFile) throws Exception {
		service.imageUpload(request, response, multiFile);
		return null;        
	}
	
	@RequestMapping(value = "/boardRegModAction", method = RequestMethod.POST)
	@ResponseBody
	public String boardRegModAction(HttpSession hs, @RequestBody Userboard_JPA param) {
		Object resultObject = hs.getAttribute("UserInfo");
		
		if(resultObject == null) {
			return null;
		}
		
		// UserInfo가 속하는 클래스의 풀네임 추출
		String classFullNm = resultObject.getClass().getName();
		String classNm = classFullNm.substring(classFullNm.lastIndexOf(".") + 1);
		
		User_JPA user_jpa = null;
		Social_JPA social_jpa = null;
		
		int main_seq = 0;
		int social_seq = 0;
		
		if(classNm.equals("User_JPA")) {
			user_jpa = (User_JPA)resultObject;
		} else if(classNm.equals("Social_JPA")) {
			social_jpa = (Social_JPA)resultObject;
			if(social_jpa.getUser() == null) {
				return null;
			}
			
		}
		
		Userboard_JPA userBoard_jpa = new Userboard_JPA();
		
		userBoard_jpa.setTitle(param.getTitle());
		userBoard_jpa.setContent(param.getContent());
		
		System.out.println("수정");
		System.out.println("타이틀 : " + param.getTitle());
		System.out.println("내용 : " + param.getContent());
		System.out.println("머야 이건 : " + social_jpa.getUsername());
		
		return "ggggggg";
	}

	@GetMapping("/userinfo")
	public String userInfo() {
		return "/userinfo";
	}

	@GetMapping("/userinfo0")
	public String userInfo0() {
		return "/userinfo0";
	}

	@GetMapping("/userinfo1")
	public String userInfo1() {
		return "/userinfo1";
	}

	@GetMapping("/userinfo2")
	public String userInfo2() {
		return "/userinfo2";
	}

	@GetMapping("/userinfo3")
	public String userInfo3() {
		return "/userinfo3";
	}

	

}

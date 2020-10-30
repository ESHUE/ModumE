package com.amolrang.modume.test;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;
import com.amolrang.modume.repository.UserBoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RestController
@Controller
public class TestController {
	@Autowired
	private TestService service;
	
	@Autowired
	UserBoardRepository userBoardRepository;

	@RequestMapping(value = "/test", produces = "text/plain;charset=UTF-8")
	public String test(Principal principal, OAuth2AuthenticationToken authentication) {
		log.info("principal:{}", principal);
		return principal.toString();
	}

	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		log.info("boardList:{}",userBoardRepository.findAll());
		model.addAttribute("list", userBoardRepository.findAll());
		return "/boardList";
	}
	
	
	
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.POST)
	public String boardDetail(Model model, @RequestBody Map<String, Object> param) {
		int USERBOARD_SEQ = (int) param.get("USERBOARD_SEQ");
		System.out.println("꾸엑 : " + USERBOARD_SEQ);
		//model.addAttribute("board", UserBoardRepository.findBy);
		return "/boardDetail";
	}
	
	
	
	

	@RequestMapping(value = "/boardRegMod", method = RequestMethod.GET)
	public String boardRegMod() {
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
	public Userboard_JPA boardRegModAction(HttpSession hs, @RequestBody Userboard_JPA param) {
		return service.boardRegModAction(hs, param);
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

package com.amolrang.modume.test;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Boardimg_JPA;
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
		List<Userboard_JPA> list = userBoardRepository.findAllByOrderByBoardseqDesc();
		//List<Userboard_JPA> list = userBoardRepository.selAllByUserseq();
		model.addAttribute("list", service.boardList(list));
		return "/boardList";
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.POST)
	public String boardDetail(Model model, @RequestBody Map<String, Object> param, HttpSession hs) {
		int boardseq = (int) param.get("boardseq");
		User_JPA loginUser = (User_JPA)hs.getAttribute("userInfo");
		System.out.println("로그인 : " + loginUser.getUserseq());
		model.addAttribute("boardDetail", userBoardRepository.findByBoardseq(boardseq));
		model.addAttribute("loginUser", loginUser);
		return "/boardDetail";
	}
	
	@RequestMapping(value = "/boardRegMod", method = RequestMethod.GET)
	public String boardRegMod(Model model, HttpSession hs) {
		User_JPA user_jpa = (User_JPA)hs.getAttribute("userInfo");
		if(user_jpa == null) {
			return null; // status 404 띄움
		}
		return "/boardRegMod";
	}
	
	@RequestMapping(value = "/boardRegMod", method = RequestMethod.POST)
	@ResponseBody
	public Userboard_JPA boardRegMod(@RequestBody Userboard_JPA param) {
		System.out.println("오는거샤?? : " + param.getBoardseq());
		Userboard_JPA ubModel = userBoardRepository.findByBoardseq(param.getBoardseq());
		log.info("찍어보쟈 findByBoardseq:{}", ubModel);
		return ubModel;
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
	public int boardRegModAction(HttpSession hs, @RequestBody Userboard_JPA param) {
		int boardseq = service.boardRegModAction(hs, param);
		return boardseq;			
	}
	
	@RequestMapping(value = "/boardDel", method = RequestMethod.GET)
	@ResponseBody
	public String boardDel(Userboard_JPA param) {
		userBoardRepository.deleteByBoardseq(param.getBoardseq());
		return null;
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

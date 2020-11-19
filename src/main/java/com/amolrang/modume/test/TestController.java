package com.amolrang.modume.test;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Comment_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;
import com.amolrang.modume.repository.BoardImgRepository;
import com.amolrang.modume.repository.CommentRepository;
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

	@Autowired
	BoardImgRepository boardImgRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	TestMapper testMapper;
	
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
		//System.out.println("로그인 : " + loginUser.getUserseq());
		
		Userboard_JPA userboard_jpa = userBoardRepository.findByBoardseq(boardseq);
		model.addAttribute("boardDetail", userboard_jpa);
		model.addAttribute("comment_cnt", testMapper.selCountComment(userboard_jpa));
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
		boardImgRepository.deleteByBoardseq(param);
		commentRepository.deleteByBoardseq(param);
		userBoardRepository.deleteByBoardseq(param.getBoardseq());
		return null;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(Model model, @RequestBody Map<Object, Object> param, HttpSession hs) {
		List<Comment_JPA> commentList = service.comment(param, hs);
		model.addAttribute("commentList", commentList);
		model.addAttribute("boardseq", (int) param.get("boardseq"));
		model.addAttribute("loginUser", (User_JPA)hs.getAttribute("userInfo"));
		
		return "/comment";
	}
	
	@RequestMapping(value = "/commentRegModAction", method = RequestMethod.POST)
	@ResponseBody
	public int commentRegModAction(HttpSession hs, @RequestBody Map<String, Object> param) {
		User_JPA user_jpa = (User_JPA)hs.getAttribute("userInfo");
		if(user_jpa == null) {
			return 0;
		}
		
		int boardseq = (int) param.get("boardseq");
		String commentcontent = (String) param.get("commentcontent");
		// int commentseq = (int) param.get("commentseq");
		int commentseq = Integer.parseInt((String) param.get("commentseq"));
		
		System.out.println("하하 boardseq : " + boardseq);
		System.out.println("하하 commentcontent : " + commentcontent);
		System.out.println("하하 commentseq : " + commentseq);
		
		
		Comment_JPA comment_jpa = new Comment_JPA();
		comment_jpa.setBoardseq(userBoardRepository.findByBoardseq(boardseq));
		comment_jpa.setCommentcontent(commentcontent);
		comment_jpa.setCommentseq(commentseq);
		comment_jpa.setUserseq(user_jpa);
		
		service.commentRegModAction(comment_jpa);
		return boardseq;			
	}
	
	@RequestMapping(value = "/commentDel", method = RequestMethod.GET)
	@ResponseBody
	public String commentDel(Comment_JPA param) {
		commentRepository.deleteByCommentseq(param.getCommentseq());
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

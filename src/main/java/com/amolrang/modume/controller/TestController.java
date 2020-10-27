package com.amolrang.modume.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amolrang.modume.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RestController
@Controller
public class TestController {
//	@Autowired
//	private TestService service;

	@RequestMapping(value = "/test", produces="text/plain;charset=UTF-8")
	public String test(Principal principal,OAuth2AuthenticationToken authentication) {
		log.info("principal:{}",principal);
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
		return "/boardRegMod";
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

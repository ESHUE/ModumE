package com.amolrang.modume.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.nimbusds.oauth2.sdk.util.StringUtils;

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
		return "/boardRegMod";
	}
	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public String imageUpload(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest multiFile) throws Exception {
		service.imageUpload(request, response, multiFile);
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

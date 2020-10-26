package com.amolrang.modume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amolrang.modume.api.UserModelGetToToken;
import com.amolrang.modume.api.twitchAPI;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JsonController {
	@Autowired
	UserService userService;

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@Autowired
	private UserModelGetToToken callApi;

	@Autowired
	private twitchAPI twitchApi;
	
	@RequestMapping(value = "/IdChk", produces = "text/plain;charset=UTF-8")
	public String IdChk(@RequestBody User_JPA param) {
		String result = "2";
		if (userService.loadUserByUsername(param.getUsername()) != null) {
			result = "3";
		}
		return String.format("%s", result);
	}

	@RequestMapping(value = "/CallVideo", produces = "text/plain;charset=UTF-8")
	public String callVideo(String follow,OAuth2AuthenticationToken authentication) {
		return twitchApi.getTwtichVideo(follow,authentication, authorizedClientService);
	}

	@RequestMapping(value = "/CallFollows", produces = "text/plain;charset=UTF-8")
	public String CallFollows(OAuth2AuthenticationToken authentication) {
		return twitchApi.getTwitchMyFollower(authentication, authorizedClientService);
	}
	
	@RequestMapping(value = "/getStreams", produces = "text/plain;charset=UTF-8")
	public String getStreams(OAuth2AuthenticationToken authentication) {
		return twitchApi.getCurrentLiveStreamer(authentication, authorizedClientService);
	}
}

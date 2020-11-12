package com.amolrang.modume.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amolrang.modume.api.GoogleAPI;
import com.amolrang.modume.api.TwitchAPI;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.AuthRepository;
import com.amolrang.modume.repository.SocialRepository;
import com.amolrang.modume.repository.UserRepository;
import com.amolrang.modume.service.UserService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JsonController {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	SocialRepository socialRepository;
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@Autowired
	private TwitchAPI twitchApi;
	
	@Autowired
	private GoogleAPI googleApi;
	
	@RequestMapping(value = "/IdChk", produces = "text/plain;charset=UTF-8")
	public String IdChk(User_JPA param) {
		log.info("param:{}",param);
		String result = "2";
		System.out.println(param.getUsername());
		if (userService.loadUserByUsername(param.getUsername()) != null) {
			log.info("user:{}",param);
			result = "3";
		}
		return String.format("%s", result);
	}
	
	@RequestMapping(value = "/CallVideo", produces = "text/plain;charset=UTF-8")
	public String callVideo(String follow,OAuth2AuthenticationToken authentication) {
		return twitchApi.getTwtichVideo(follow,authentication, authorizedClientService);
	}

	@RequestMapping(value = "/CallFollows", produces = "text/plain;charset=UTF-8")
	public String CallFollows(OAuth2AuthenticationToken authentication,HttpSession hs) {
		return twitchApi.getTwitchMyFollower(authentication, authorizedClientService,hs);
	}
	
	@RequestMapping(value = "/getStreams", produces = "text/plain;charset=UTF-8")
	public String getStreams(OAuth2AuthenticationToken authentication, HttpSession hs) {
		return twitchApi.getCurrentLiveStreamer(authentication, authorizedClientService, hs);
	}
	
	@RequestMapping(value = "/getChat", produces = "text/plain;charset=UTF-8")
	public String getChatting(OAuth2AuthenticationToken authentication, HttpSession hs) {
		return twitchApi.getChatting(authentication, authorizedClientService, hs);
	}
	
	@RequestMapping(value = "/getYoutubeFollower", produces = "text/plain;charset=UTF-8")
	public String getYoutubeFollower(OAuth2AuthenticationToken authentications) {
		return googleApi.getYoutubeMyFollower(authentications, authorizedClientService);
	}
	
	@RequestMapping(value = "/googleSearch", produces = "text/plain;charset=UTF-8")
	public String googleSearch(String keyword) {
		return googleApi.searchYoutube(authorizedClientService, keyword);
	}
	
	@RequestMapping(value = "/autoJoin",produces = "text/plain;charset=UTF-8")
	public void autoJoin(String streamerID,HttpSession hs, boolean bPlay) {
		hs.setAttribute("streamerID", streamerID);
		hs.setAttribute("bPlay", bPlay);
		log.info("bPlay:{}",bPlay);
	}
	
	@RequestMapping(value = "/curUserName",produces = "text/plain;charset=UTF-8")
	public String curUserName(Principal principal) {
		log.info("principal : {}",principal);
		User_JPA user = userRepository.findByUsername(principal.getName());
		log.info("Username:{}",user == null ? socialRepository.findBysocialusername(principal.getName()).getUsername() : user.getUsername());
		return user == null ? socialRepository.findBysocialusername(principal.getName()).getUsername() : user.getNickname();
	}
	@RequestMapping(value = "/setRoomId" ,produces = "text/plain;charset=UTF-8")
	public void setRoomId(String roomId ,String title, HttpSession hs) throws UnsupportedEncodingException {
		log.info("setyouTubeRoomId:{}",roomId);
		log.info("setyouTubeTitle:{}",title);
		title = URLDecoder.decode(title,"UTF-8");
		hs.setAttribute("youTubeRoomId", roomId);
		hs.setAttribute("youTubeTitle", title);
		
	}
	
	@RequestMapping(value = "/getRoomId" ,produces = "text/plain;charset=UTF-8")
	public String getRoomId(HttpSession hs) throws UnsupportedEncodingException {
		log.info("getyouTubeRoomId:{}",(String)hs.getAttribute("youTubeRoomId"));
		log.info("getyouTubeTitle:{}",(String)hs.getAttribute("youTubeTitle"));
		Map result = new HashMap();
		if( hs.getAttribute("youTubeRoomId") != null ) {
			result.put("youTubeRoomId", (String)hs.getAttribute("youTubeRoomId"));
		}
		if( hs.getAttribute("youTubeTitle") != null ) {
			result.put("youTubeTitle", (String)hs.getAttribute("youTubeTitle"));
		}
		Gson gson = new Gson();
		log.info("result:{}",result);
		return gson.toJson(result);
	}
	
	@RequestMapping(value="/getStreamerId" ,produces = "text/plain;charset=UTF-8")
	public String getStreamerId(HttpSession hs) {
		Map result = new HashMap();
		result.put("streamerId", (String)hs.getAttribute("streamerID"));
		Gson gson = new Gson();
		log.info("result:{}",result);
		return gson.toJson(result);
	}
}

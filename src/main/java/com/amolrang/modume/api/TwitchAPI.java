package com.amolrang.modume.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.UserModel;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.SocialRepository;
import com.amolrang.modume.repository.UserRepository;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class TwitchAPI {
	private Gson gson = new Gson();
	private RestTemplate restTemplate =null;
	private ResponseEntity<Map> response = null;
	private HttpHeaders headers = null;

	@Autowired
	private SocialRepository socialRepository;
	@Autowired
	UserRepository userRepository;
	/**
	 * 트위치 로그인 성공 -> 나의 팔로우 가져옴 -> 20개중 라이브 방송 필터링 -> 라이브 방송 목록 표시 + 추천 생방송 표시 = 20개
	 */
	
	public String getTwitchMyFollower(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService,HttpSession hs) {
		String url = "https://api.twitch.tv/helix/users/follows";
		String query = "?from_id="+authentication.getPrincipal().getName();
		String token = null;
		String clientId = null;
		User_JPA loginedUser = (User_JPA)hs.getAttribute("userInfo");
		List<Social_JPA> social_JPA_List= socialRepository.findAllByUserseq(loginedUser);
		if(social_JPA_List != null) {
			for(Social_JPA sns : social_JPA_List) {
				if(sns.getSns().equals("twitch")) {
					token = sns.getToken();
					clientId = sns.getClientid();
					break;
				}
			}
		}
		
		newHeader(clientId,authAuthorizedClientService,token);
		restTemplateExchange(url, query);

		LinkedHashMap data = (LinkedHashMap) response.getBody();
		ArrayList<Map> list = (ArrayList<Map>) data.get("data");
		log.info("FollowerLog:{}",data);
		log.info("FollowTestLog:{}",list);
		hs.setAttribute("followList", list);
//		Map<?,?> test = (Map<?,?>)data.get("data");
//		log.info("FollowerTestLog:{}",test.size());
		return gson.toJson(data);
	}
	
	public String getTwtichVideo(String follow,OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/videos";
		String query = "?user_id="+follow;
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		Map data = response.getBody();
		log.info("VideoLog:{}",data);
	
//		Map videoLog = (Map)data.get("from_id");
//		log.info("videoLog:{}",videoLog);
		return gson.toJson(data);
	}
	
	public String getCurrentLiveStreamer(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService,HttpSession hs) {
		String url = "https://api.twitch.tv/helix/streams?language=ko";
		String query = "";
		String token = null;
		String clientId = null;
		User_JPA loginedUser = (User_JPA)hs.getAttribute("userInfo");
		List<Social_JPA> social_JPA_List= socialRepository.findAllByUserseq(loginedUser);
		if(social_JPA_List != null) {
			for(Social_JPA sns : social_JPA_List) {
				if(sns.getSns().equals("twitch")) {
					token = sns.getToken();
					clientId = sns.getClientid();
					break;
				}
			}
		}
		
		newHeader(clientId,authAuthorizedClientService,token);
		restTemplateExchange(url, query);

		LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) response.getBody();
		ArrayList<Map<?,?>> list = (ArrayList<Map<?,?>>) data.get("data");
		log.info("LiveStreamer:{}",data);
//		for(int i=0; i<list.size(); i++) {
//			log.info("LiveTestLog:{}",list.get(i));
//		}
		hs.setAttribute("LiveStream", list);
		return gson.toJson(data);
	}
	
	public String getChatting(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService,HttpSession hs) {
		String url = "https://www.twitch.tv/embed/43691/chat";
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, "");
		Map data = response.getBody();
//		for(int i=0; i<list.size(); i++) {
//			String query = (String) list.get(i).get("user_id") + "/chat?parent=streamernews.example.com";
//			newHeader(authentication,authAuthorizedClientService);
//			restTemplateExchange(url, query);
//			data = response.getBody();
//		}
//		log.info("ChatLog:{}",data);
		return gson.toJson(data);
	}
	
	private ResponseEntity<Map> restTemplateExchange(String url, String query){
		restTemplate = new RestTemplate();
		return response = restTemplate.exchange(url+query, HttpMethod.GET, new HttpEntity(headers), Map.class);
	}
	
	private HttpHeaders newHeader(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		headers = new HttpHeaders();
		OAuth2AuthorizedClient client = authAuthorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		return newHeader(client.getClientRegistration().getClientId(),authAuthorizedClientService,client.getAccessToken().getTokenValue());
	}
	private HttpHeaders newHeader(String clientId,
			OAuth2AuthorizedClientService authAuthorizedClientService,String Token) {
		headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + Token);
		headers.add("Client-Id", clientId);
		return headers;
	}
}























package com.amolrang.modume.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class TwitchAPI {
	private Gson gson = new Gson();
	private RestTemplate restTemplate =null;
	private ResponseEntity<Map> response = null;
	private HttpHeaders headers = null;

	/**
	 * 트위치 로그인 성공 -> 나의 팔로우 가져옴 -> 20개중 라이브 방송 필터링 -> 라이브 방송 목록 표시 + 추천 생방송 표시 = 20개
	 */
	
	public String getTwitchMyFollower(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/users/follows";
		String query = "?from_id="+authentication.getPrincipal().getName();
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		LinkedHashMap data = (LinkedHashMap) response.getBody();
		ArrayList<Map> list = (ArrayList<Map>) data.get("data");
		log.info("FollowerLog:{}",data);
		log.info("TestLog:{}",list);
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
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/streams?language=ko";
		String query = "";
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) response.getBody();
		ArrayList<Map<?,?>> list = (ArrayList<Map<?,?>>) data.get("data");
		log.info("LiveStreamer:{}",data);
		for(int i=0; i<list.size(); i++) {
			log.info("TestLog:{}",list.get(i));
		}
		
		
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
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
		headers.add("Client-Id", client.getClientRegistration().getClientId());
		return headers;
	}
}























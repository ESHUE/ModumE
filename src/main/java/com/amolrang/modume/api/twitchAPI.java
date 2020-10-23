package com.amolrang.modume.api;

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
public class twitchAPI {
	private Gson gson = new Gson();
	private RestTemplate restTemplate = new RestTemplate();
	private ResponseEntity<Map> response = null;
	private HttpHeaders headers = null;

	/**
	 * 트위치 로그인 성공 -> 나의 팔로우 가져옴 -> 20개중 라이브 방송 필터링 -> 라이브 방송 목록 표시 + 추천 생방송 표시 = 20개
	 */
	
	public String getTwitchMyFollower(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/users/follows?from_id=";
		String query = authentication.getPrincipal().getName();
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		Map data = response.getBody();
		
		return gson.toJson(data);
	}
	
	public String getTwtichVideo(String follow,OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/videos?user_id=";
		String query = follow;
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		Map data = response.getBody();
		
		return gson.toJson(data);
	}
	
	public String getCurrentLiveStreamer(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://api.twitch.tv/helix/streams?language=ko";
		String query = "";
		
		newHeader(authentication,authAuthorizedClientService);
		restTemplateExchange(url, query);

		Map data = response.getBody();
		
		return gson.toJson(data);
	}
	
	
	
	private ResponseEntity<Map> restTemplateExchange(String url, String query){
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























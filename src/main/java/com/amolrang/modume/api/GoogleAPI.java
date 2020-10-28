package com.amolrang.modume.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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

@Service
public class GoogleAPI {

	@Value("${google.youtube.dataApiKey}")
	private String apikey;
	private Gson gson = new Gson();
	private RestTemplate restTemplate =null;
	private ResponseEntity<Map> response = null;
	private HttpHeaders headers = null;
	
	public String getYoutubeMyFollower(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		String url = "https://www.googleapis.com/youtube/v3/subscriptions";
		String query = "?part=snippet,contentDetails&mine=true&key=" + apikey;
		newHeader(authentication, authAuthorizedClientService);
		restTemplateExchange(url, query);
		Map data = response.getBody();
		
		return gson.toJson(data);
	}
	
	private ResponseEntity<Map> restTemplateExchange(String url, String query) {
		restTemplate = new RestTemplate();
		return response = restTemplate.exchange(url + query, HttpMethod.GET, new HttpEntity(headers), Map.class);
	}

	private HttpHeaders newHeader(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService authAuthorizedClientService) {
		headers = new HttpHeaders();
		OAuth2AuthorizedClient client = authAuthorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
		headers.add(HttpHeaders.ACCEPT, "application/json");
		return headers;
	}
}

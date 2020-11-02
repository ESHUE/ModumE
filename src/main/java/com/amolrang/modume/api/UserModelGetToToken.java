package com.amolrang.modume.api;

import java.util.HashMap;
import java.util.Map;

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
import com.amolrang.modume.repository.SocialRepository;
import com.amolrang.modume.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserModelGetToToken {
	@Autowired
	SocialRepository socialRepository;
	
	public Social_JPA CallUserInfoToJson(OAuth2AuthenticationToken authentication,
			OAuth2AuthorizedClientService auth2AuthorizedClientService) {
		OAuth2AuthorizedClient client = auth2AuthorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		log.info("access token:{}", client.getAccessToken().getTokenValue());
		String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
		//SocialModel socialModel = new SocialModel();
		Social_JPA socialModel = new Social_JPA();
		if (!StringUtils.isEmpty(userInfoEndpointUri)){
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

			// 유저정보 조회
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<Map> response = restTemplate.exchange(
					StringUtils.siteUrlCustom(authentication.getAuthorizedClientRegistrationId(), userInfoEndpointUri),
					HttpMethod.GET, entity, Map.class);

			log.info("response:{}", response);
			log.info("userInfo{}", response.getBody());

			//유저 정보 추출
			Map<?, ?> userInfo = response.getBody();
			Map<Object, Object> userInfoMap = new HashMap<>();
			
			//뽑은 정보들 넣을 변수들
			String id = null;
			String name = null;
			String email = null;
			String sns = "modume";
			//어느 사이트와 연동해서 로그인을 했느냐에 따라 switch문
			log.info("sns정보:{}",authentication.getAuthorizedClientRegistrationId());
			switch (authentication.getAuthorizedClientRegistrationId()) {
			//각각 필요한 정보 뽑기(id, name, email >> twitch는 email없음)
			case "naver":
				Map<?, ?> naverUserInfo = (Map<?, ?>) userInfo.get("response");
				log.info("userinfo_id :{}", naverUserInfo.get("id"));
				log.info("userinfo_nickname :{}", naverUserInfo.get("nickname"));
				log.info("userinfo_email :{}", naverUserInfo.get("email"));
				id = (String) naverUserInfo.get("id");
				name = (String) naverUserInfo.get("nickname");
				email = (String) naverUserInfo.get("email");
				sns = "naver";
				// json obj
				// obj => 추출
				// return할때 json으로 정리해서 보내기.
				break;
			case "kakao":
				Map<?, ?> kakaoInfo = (Map<?, ?>) userInfo.get("kakao_account");
				Map<?, ?> kakaoUserInfo = (Map<?, ?>) kakaoInfo.get("profile");
				log.info("userinfo_id :{}", userInfo.get("id"));
				log.info("userinfo_nickname :{}", kakaoUserInfo.get("nickname"));
				log.info("userinfo_email :{}", kakaoInfo.get("email"));
				id = String.valueOf(userInfo.get("id"));
				name = (String) kakaoUserInfo.get("nickname");
				email = (String) kakaoInfo.get("email");
				sns = "kakao";
				break;
			case "google":
				log.info("userinfo_id :{}", userInfo.get("sub"));
				log.info("userinfo_name :{}", userInfo.get("name"));
				log.info("userinfo_email :{}", userInfo.get("email"));
				id = String.format("%s", userInfo.get("sub"));
				name = (String) userInfo.get("name");
				email = (String) userInfo.get("email");
				sns = "google";
				break;

			case "twitch":
				log.info("userinfo_id :{}", userInfo.get("sub"));
				log.info("userinfo_name :{}", userInfo.get("preferred_username"));
				id = (String) userInfo.get("sub");
				name = (String) userInfo.get("preferred_username");
				sns = "twitch";
				break;
			}
			
			//2020.10.26 소셜 로그인시 정보  SocialModel에 담아버림
//			if(socialRepository.findBySocialUsername(id)==null) {
				//id를 db에서 찾지 못했을 때
//				socialModel.setS_id(id);
//				socialModel.setUsername(name);
//				socialModel.setSns(sns);
//				log.info("socialModel:{}",socialModel);
//			}
			socialModel.setSocialusername(id);
			socialModel.setSns(sns);
			socialModel.setUsername(name);
		}
		return socialModel;
	}
}

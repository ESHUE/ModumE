package com.amolrang.modume.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	
	//클라이언트 <> 웹 소켓 연결하는데 사용할 엔드 포인트 등록
	//withSocketJS() 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화
	//Fallback = 어떤 기능이 약해지거나 제대로 동작하지 않을때, 이에 대처하는 기능 또는 동작
	//STOMP = Simple Test Messaging Protocol약어
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registory) {
		registory.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}
	

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registory) {
		registory.setApplicationDestinationPrefixes("/app");
		registory.enableSimpleBroker("/topic");
	}
}

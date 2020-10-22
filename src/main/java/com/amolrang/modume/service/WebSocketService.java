package com.amolrang.modume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.amolrang.modume.model.ChatModel;
import com.amolrang.modume.type.MessageType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketService {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("Received a new web socket connection");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String username = (String)headAccessor.getSessionAttributes().get("username");
		
		if(username != null) {
			log.info("User Disconnected :" + username);
			
			ChatModel chatMessage = new ChatModel();
			chatMessage.setType(MessageType.LEAVE);
			chatMessage.setSender(username);
			
			messagingTemplate.convertAndSend("/topic/public",chatMessage);
		}
	}
}

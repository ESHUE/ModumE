package com.amolrang.modume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import com.amolrang.modume.model.ChatModel;
import com.amolrang.modume.service.WebSocketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WebSocketController {

	@Autowired
	private WebSocketService service;

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatModel sendMessage(@Payload ChatModel chatMessage) {
		log.info("chatMessage : {}" + chatMessage.toString());
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatModel addUser(@Payload ChatModel chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		log.info("chatMessage : {}" + chatMessage.toString());
		return chatMessage;
	}
}

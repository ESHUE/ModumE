package com.amolrang.modume.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.amolrang.modume.type.ChatMessage;
import com.amolrang.modume.type.MessageType;
import com.amolrang.modume.utils.MessageSendUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class ChatRoom {
	 	//private String id;
	    private String name;
	    private String userName;
	    
	    private Set<WebSocketSession> sessions = new HashSet<>();

	    public static ChatRoom create(String name,String userName/*, String id*/) {
	        ChatRoom created = new ChatRoom();
	        //created.id = id;
	        System.out.println(name);
	        System.out.println(userName);
	        created.name = name;
	        created.userName = userName;
	        return created;
	    }

	    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) {
	    	System.out.println(chatMessage.getType());
	        if (chatMessage.getType() == MessageType.JOIN) {
	            join(session);
	            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장했습니다.");
	        }else if(chatMessage.getType() == MessageType.LEAVE) {
	        	leave(session);
	        	chatMessage.setMessage(chatMessage.getWriter() + "님이 퇴장했습니다.");
	        }
	        
	        send(chatMessage, objectMapper);
	    }

	    private void leave(WebSocketSession session) {
			// TODO Auto-generated method stub
	    	sessions.add(session);
		}

		private void join(WebSocketSession session) {
	        sessions.add(session);
	    }

	    private <T> void send(T messageObject, ObjectMapper objectMapper) {
	        try {
	            TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
	            sessions.parallelStream().forEach(session -> MessageSendUtils.sendMessage(session, message));
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	    }

	    public void remove(WebSocketSession target) {

	        String targetId = target.getId();
	        sessions.removeIf(session -> session.getId().equals(targetId));
	    }
}
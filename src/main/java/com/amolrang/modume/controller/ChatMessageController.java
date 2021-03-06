package com.amolrang.modume.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.amolrang.modume.model.Chat_JPA;
import com.amolrang.modume.repository.ChatRepository;
import com.amolrang.modume.type.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
public class ChatMessageController {

    private final SimpMessagingTemplate template;
    @Autowired
    ChatRepository chatRepository;
    
    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 입장하셨습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
    	System.out.println("messageId:" + message.getChatRoomId());
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
        Chat_JPA chat = new Chat_JPA();
        chat.setCtnt(message.getMessage());
        chat.setStreamerId(message.getChatRoomId());
        chat.setUserName(message.getWriter());
        chat.setStreamerId(message.getStreamerId());
        chatRepository.save(chat);
    }
    
    @MessageMapping("/chat/leave")
    public void leave(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 퇴장하셨습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }
}

package com.amolrang.modume.controller;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amolrang.modume.model.ChatRoom;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.repository.ChatRoomRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository repository;
    private final String listViewName;
    private final String detailViewName;
    private final AtomicInteger seq = new AtomicInteger(0);

    @Autowired
    public ChatRoomController(ChatRoomRepository repository, @Value("${viewname.chatroom.list}") String listViewName, @Value("${viewname.chatroom.detail}") String detailViewName) {
        this.repository = repository;
        this.listViewName = listViewName;
        this.detailViewName = detailViewName;
    }

    @GetMapping("/rooms")
    public String rooms(Model model,HttpSession hs,User_JPA userModel) {
    	repository.CreateRoom("",hs);
    	userModel = (User_JPA)hs.getAttribute("userInfo");
    	//값 전달을 위해 미리 넘김
        model.addAttribute("rooms", repository.getChatRooms());
        model.addAttribute("member", userModel.getUsername());
        return listViewName;
    }

    @GetMapping("/rooms/{name}")
    public String room(@PathVariable String name, Model model, HttpSession hs) {
    	name = repository.CreateRoom("",hs);
    	log.info("name:{}",name);
        ChatRoom room = repository.getChatRoom(name);
        log.info("room:{}",room);
        //model.addAttribute("room", room);
        //채팅방 들어갔을 때 닉네임으로 들고오기 
        //log.info("room:{}",room);
        return detailViewName;
    }
}

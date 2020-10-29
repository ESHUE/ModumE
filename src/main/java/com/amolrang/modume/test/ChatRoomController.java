package com.amolrang.modume.test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;

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
    public String rooms(Model model,HttpSession hs) {
    	repository.CreateRoom("",hs);
        model.addAttribute("rooms", repository.getChatRooms());
        return listViewName;
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model, HttpSession hs, Social_JPA socialModel) {
    	log.info("id:{}",id);
    	socialModel = (Social_JPA)hs.getAttribute("UserInfoJson");
        ChatRoom room = repository.getChatRoom(id);
        model.addAttribute("room", room);
        //채팅방 들어갔을 때 닉네임으로 들고오기 
        model.addAttribute("member", socialModel.getUsername());
        log.info("room:{}",room);
        return detailViewName;
    }
}

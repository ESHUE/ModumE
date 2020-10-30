package com.amolrang.modume.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;

@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;
	@Getter
	private Collection<ChatRoom> chatRooms;
	public void CreateRoom(String roomName,HttpSession hs) {
//		ChatRoom[] roomList = {ChatRoom.create("전체 채팅방"),ChatRoom.create("?????")};
		ArrayList<Map> list = (ArrayList<Map>)hs.getAttribute("LiveStream");
		ChatRoom[] roomList  = new ChatRoom[list.size()];
		//roomList[0] = ChatRoom.create("전체 채팅방");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			roomList[i] = ChatRoom.create((String)list.get(i).get("user_name"));
			System.out.println("roomList: " + roomList[i]);
		}
		chatRoomMap = Collections
				.unmodifiableMap(Stream.of(roomList)
						.collect(Collectors.toMap(ChatRoom::getId, Function.identity())));
		System.out.println(chatRoomMap);
		chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
	}
	
	
	public ChatRoom getChatRoom(String id) {
		return chatRoomMap.get(id);
	}

	public void remove(WebSocketSession session) {
		this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
	}
}
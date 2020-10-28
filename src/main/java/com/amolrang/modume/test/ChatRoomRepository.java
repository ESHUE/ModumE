package com.amolrang.modume.test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;

@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;
	@Getter
	private Collection<ChatRoom> chatRooms;
	private List<ChatRoom> roomList = null;
	public ChatRoomRepository() {
		roomList.add(ChatRoom.create("전체 채팅방"));
	}
	
	public void CreateRoom(String roomName) {
		chatRoomMap = Collections
				.unmodifiableList(Stream.of(roomList)
						.collect(Collectors.toList(ChatRoom::getId, Function.identity())));
		chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
	}
	

	public ChatRoom getChatRoom(String id) {
		return chatRoomMap.get(id);
	}

	public void remove(WebSocketSession session) {
		this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
	}
}
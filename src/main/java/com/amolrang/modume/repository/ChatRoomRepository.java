package com.amolrang.modume.repository;

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

import com.amolrang.modume.model.ChatRoom;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;
	@Getter
	private Collection<ChatRoom> chatRooms;
	public String CreateRoom(String roomName,HttpSession hs) {
//		ChatRoom[] roomList = {ChatRoom.create("전체 채팅방"),ChatRoom.create("?????")};
		ArrayList<Map> list = (ArrayList<Map>)hs.getAttribute("LiveStream");
		String JsonId = (String)hs.getAttribute("streamerID");
		ChatRoom roomList = ChatRoom.create("All", "전체채팅방");;
		String streamerID = null;
		String check = "false";
		if(list != null && (String)hs.getAttribute("youTubeRoomId") == null) {
			System.out.println(list.size());
			for(int i=0; i<list.size(); i++) {
				//방송하는 사람의 이름 + 닉네임
				String userName = (String)list.get(i).get("user_name");
				String url = (String)list.get(i).get("thumbnail_url");
				streamerID = url.substring(url.indexOf("user_") + 5, url.lastIndexOf("-{width}"));
				if(streamerID.equals(JsonId)) {
					roomList= ChatRoom.create(JsonId,userName);
				}
			}
		} /*
			 * else if((boolean)hs.getAttribute("check").equals(check)){ JsonId =
			 * (String)hs.getAttribute("youTubeRoomId"); roomList =
			 * ChatRoom.create(JsonId,(String)hs.getAttribute("youTubeTitle")); }
			 */
		else {
			roomList = ChatRoom.create((String)hs.getAttribute("youTubeRoomId"), (String)hs.getAttribute("youTubeTitle"));
			hs.setAttribute("youTubeTitle", null);
			hs.setAttribute("youTubeRoomId", null);
		}
		log.info("roomList:{}",roomList);
		if( roomList != null ) {
			chatRoomMap = Collections
					.unmodifiableMap(Stream.of(roomList)
							.collect(Collectors.toMap(ChatRoom::getName, Function.identity())));
			//System.out.println(chatRoomMap);
			chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
			log.info("chatRooms:{}",chatRooms);
		}
		log.info("chatRoomMap:{}",chatRoomMap);
		hs.setAttribute("room", roomList);
		return JsonId;
	}
	
	public ChatRoom getChatRoom(String name) {
		log.info("getChatRoom_name:{}",name);
		return chatRoomMap.get(name);
	}

	public void remove(WebSocketSession session) {
		this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
	}
}
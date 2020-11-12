package com.amolrang.modume.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	private String streamerId;
    private String chatRoomId;
    private String writer;
    private String message;
    private MessageType type;
}

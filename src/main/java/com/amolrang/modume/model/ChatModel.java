package com.amolrang.modume.model;

import com.amolrang.modume.type.MessageType;

import lombok.Data;

@Data
public class ChatModel {
	private MessageType type;
	private String content;
	private String sender;
}

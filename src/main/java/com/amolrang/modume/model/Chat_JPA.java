
package com.amolrang.modume.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Chat_JPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CHAT_SEQ;

}
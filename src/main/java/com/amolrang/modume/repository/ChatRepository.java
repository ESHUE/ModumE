package com.amolrang.modume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amolrang.modume.model.Chat_JPA;

public interface ChatRepository extends JpaRepository<Chat_JPA, Integer>{}

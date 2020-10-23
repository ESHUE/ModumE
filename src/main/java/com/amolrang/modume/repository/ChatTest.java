package com.amolrang.modume.repository;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.Data;

@Entity
@Data
public class ChatTest {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String addr;
    public ChatTest() {
    }
    public ChatTest(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
    public interface ChatTestRepository extends JpaRepository<ChatTest, Long> {
    }
}
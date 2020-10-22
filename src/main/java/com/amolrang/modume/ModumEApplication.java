package com.amolrang.modume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModumEApplication {
	@Autowired
	
	public static void main(String[] args) {
		SpringApplication.run(ModumEApplication.class, args);
		
	}

}

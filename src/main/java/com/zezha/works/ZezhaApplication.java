package com.zezha.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZezhaApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/zezha");
		SpringApplication.run(ZezhaApplication.class, args);
	}
}
	 
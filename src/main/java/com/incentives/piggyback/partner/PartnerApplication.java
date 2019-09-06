package com.incentives.piggyback.partner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class PartnerApplication {
	
//	@RequestMapping("/")
//	public String home() {
//		return "Hello, from partner service in Docker";
//	}

	public static void main(String[] args) {
		SpringApplication.run(PartnerApplication.class, args);
	}

}
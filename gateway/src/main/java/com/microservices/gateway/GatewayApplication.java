package com.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(GatewayApplication.class, args);

		// Manually calling the method
		GatewayApplication app = new GatewayApplication();
		ResponseEntity<String> response = app.hellowWorld();
		System.out.println(response.getBody());
	}
	public ResponseEntity<String> hellowWorld() {
		String s = "chetan";
		return ResponseEntity.ok("Hello, " + s + "!");
	}

}

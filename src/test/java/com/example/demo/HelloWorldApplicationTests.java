package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloWorldApplicationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MainController mainController;
	
	@Test
	void contextLoads() {
		assertThat(mainController).isNotNull();
	}
	
	@Test
	void testHello() {
		String url = String.format("http://localhost:%d/", port);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertThat(response.getBody()).contains("hello, world");
	}
}

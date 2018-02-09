package com.akopiants.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScriptResourceTestIT {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void test() {
		String s = "print('ABC')";
		HttpEntity<String> entity = new HttpEntity<>(s, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/script"), HttpMethod.POST, entity,
				String.class);
		String actual = response.getBody();
		// actual string contains 2 invisible char(character's numeric value = -1, it is /n)
		System.out.println(actual.substring(0, actual.length() - 2));
		Assert.assertTrue(actual.substring(0, actual.length() - 2).equals("ABC"));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}

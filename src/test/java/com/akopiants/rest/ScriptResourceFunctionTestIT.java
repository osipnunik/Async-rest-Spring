package com.akopiants.rest;

import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScriptResourceFunctionTestIT {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void test() {
		String s = "var fun1 = function(name) {print('Hi there from Javascript, ' + name) ;"
				+ "return \"greeting from javascript\";};";
		HttpEntity<String> entity = new HttpEntity<>(s, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("script/fun1"), HttpMethod.POST, entity,
				String.class);
		String actual = response.getBody();
		// actual string contains 2 invisible char(character's numeric value = -1, it is /n)
		System.out.println(actual.substring(0, actual.length() ));
		Assert.assertTrue(actual.equals("greeting from javascript"));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}

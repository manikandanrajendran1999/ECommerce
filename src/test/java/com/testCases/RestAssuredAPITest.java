package com.testCases;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class RestAssuredAPITest {

	@Test
	private void tc1() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("x-api-key", "reqres-free-v1");
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mail", "mani.kandan1@gmail.com");
		body.put("first_name", "mani");
		body.put("last_name", "manii");
		
		given()
		.contentType("application/json").headers(data).body(body)
		.when()
//		.get("https://reqres.in/api/users?page=1&id=1")
		.get("https://reqres.in/api/users/819")
		.then()
		.statusCode(200)
        .log().all();
	}
	
	@Test(enabled = false)
	private void tc2() {
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("x-api-key", "reqres-free-v1");
		
		//headers
		given()
		.contentType("application/json")
		.headers(headers)
		
		//request api
		.when().
		get("https://reqres.in/api/users")
		
		//Response
		.then()
		.statusCode(200)
		.body("page", equalTo(1))
		.body("data[0].first_name", equalTo("George"))
		.log()
		.all();
	}
}

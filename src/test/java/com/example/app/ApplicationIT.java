package com.example.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {

  @LocalServerPort
  int port;

	@Test
	public void verifyGreetingCall() {
	  String name = "Susan";
    given()
        .when()
        .get("http://localhost:"+port+"/greeting?name="+name)
        .then()
        .statusCode(200)
        .and()
        .body(equalTo("{\"greeting\":\"Hello, "+name+"!\"}")
        );
	}

}

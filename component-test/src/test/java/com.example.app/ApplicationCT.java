package com.example.app;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApplicationCT {

  // Port exposed by the Docker container where the App runs
  int port = 8090;

  @Test
  public void verifyExposedGreetingCall() {
    String name = "Susan";
    given()
        .when()
        .get("http://localhost:" + port + "/greeting?name=" + name)
        .then()
        .statusCode(200)
        .and()
        .body(equalTo("{\"greeting\":\"Hello, " + name + "!\"}")
        );
  }
}

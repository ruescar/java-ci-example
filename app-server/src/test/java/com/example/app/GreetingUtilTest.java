package com.example.app;

import org.junit.Test;
import static com.example.app.GreetingUtil.buildGreeting;
import static org.junit.Assert.assertEquals;

public class GreetingUtilTest {
  @Test
  public void verifyGreeringBuild(){
    assertEquals(buildGreeting("Tom").getGreeting(), "Hello, Tom!");
  }
}

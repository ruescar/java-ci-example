package com.example.app;

public class GreetingUtil {

  private static final String template = "Hello, %s!";

  public static Greeting buildGreeting(String name){
    return new Greeting(String.format(template, name));
  }

}

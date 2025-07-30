package com.example.app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GreetingUtil {

  private static final String template = "Hello, %s!";
  private static final Lock lock = new ReentrantLock();

    public static Greeting buildGreeting(String name) {
    lock.lock();
    try {
        return new Greeting(String.format(template, name));
    } finally {
      lock.unlock();
    }
  }
}

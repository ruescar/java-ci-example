package com.example.app;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

import static com.example.app.GreetingUtil.buildGreeting;
import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.FORBIDDEN;

@JCStressTest
@Outcome(id = "0, 0", expect = FORBIDDEN, desc = "Not applicable. The test must never return 0.")
@Outcome(id = "-1, -1", expect = FORBIDDEN, desc = "Unexpected Outcome.")
@Outcome(id = "1, 1", expect = ACCEPTABLE, desc = "Both Threads processed expected greeting")
@Outcome(id = "1, 2", expect = FORBIDDEN, desc = "Second Thread processed unexpected greeting")
@Outcome(id = "2, 1", expect = FORBIDDEN, desc = "First Thread processed unexpected greeting")
@Outcome(id = "2, 2", expect = FORBIDDEN, desc = "Both Threads processed unexpected greeting")
public class ApplicationJcStressTest {

  @State
  public static class Holder {

    int run(final String name) {
      if (buildGreeting(name).getGreeting().contains(name)) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  @Actor
  public void actor1(Holder stateHolder, II_Result r) {
    r.r1 = stateHolder.run("Tom");
  }

  @Actor
  public void actor2(Holder stateHolder, II_Result r) {
    r.r2 = stateHolder.run("Susan");
  }

}

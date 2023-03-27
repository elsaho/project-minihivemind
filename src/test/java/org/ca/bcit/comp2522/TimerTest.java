package org.ca.bcit.comp2522;

import org.ca.bcit.comp2522.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

  Timer timerTest;

  @BeforeEach
  public void setUp() {
    timerTest = Timer.getInstance();
  }

  @Test
  public void testGetTimer() {
    assert(timerTest.getStart() == 0);
  }

  @Test
  public void testSetTimer() {
    timerTest.setStart(1000);
    assert(timerTest.getStart() == 1000);
  }

  @Test
  public void testSetRemaining() {
    timerTest.setRemaining(1000);
    assert(timerTest.getRemaining() == 1000);
  }

  @Test
  public void testTimeToString() {
    timerTest.setRemaining(1000);
    assert(timerTest.timeToString().equals("01"));
  }

  @Test
  public void testGetInstance() {
    Timer timerTest2 = Timer.getInstance();
    assert(timerTest2.getStart() == 0);
  }

}

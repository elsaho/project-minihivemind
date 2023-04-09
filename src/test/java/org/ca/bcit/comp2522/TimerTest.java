package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {
  GameWindow window;
  Timer timerTest;

  @BeforeEach
  public void setUp() {
    window = new GameWindow();
    timerTest = Timer.getInstance(window);
  }

  @Test
  public void testGetTimer() {
    assertEquals(90000, timerTest.getStart());
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
    GameWindow window = new GameWindow();
    Timer timerTest2 = Timer.getInstance(window);
    assertEquals(90000, timerTest2.getStart());
  }

}

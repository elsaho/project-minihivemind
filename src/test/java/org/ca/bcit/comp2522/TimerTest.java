package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {
  GameWindow window;
  Timer timerTest;
  GameWindow gameWindowTest;

  @BeforeEach
  public void setUp() {
    gameWindowTest = new GameWindow();
    PApplet.runSketch(new String[]{"GameWindowTest"}, gameWindowTest);
    timerTest = Timer.getInstance(gameWindowTest);
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
}

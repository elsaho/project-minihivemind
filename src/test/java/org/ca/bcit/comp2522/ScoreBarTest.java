package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreBarTest {

  ScoreBar scoreBarTest;
  @BeforeEach
  public void setUp() {
    scoreBarTest = ScoreBar.getInstance();
  }

  @Test
  public void testScoreBar() {
    assert(scoreBarTest.getValue() == 0);
  }

  @Test
  public void testFinishedLevel() {
    scoreBarTest.finishedLevel(1);
    assert(scoreBarTest.getValue() == 100);
  }

  @Test
  public void testAddScore() {
    scoreBarTest.addScore(100);
    assert(scoreBarTest.getValue() == 100);
  }

  @Test
  public void testGetInstance() {
    ScoreBar scoreBarTest2 = ScoreBar.getInstance();
    assert(scoreBarTest2.getValue() == 0);
  }
}

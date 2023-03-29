package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LivesTest {

  Lives lives;

  @BeforeEach
  public void setUp() {
    lives = Lives.getInstance();
  }

  @Test
  public void testLives() {
    assert(lives.getLives() == 3);
  }

  @Test
  public void testLoseLife() {
    lives.loseLife();
    assert(lives.getLives() == 2);
  }

  @Test
  public void testGainLife() {
    lives.gainLife();
    assert(lives.getLives() == 4);
  }

  @Test
  public void testSetLives() {
    lives.setLives(5);
    assert(lives.getLives() == 5);
  }

  @Test
  public void testGetInstance() {
    Lives lives2 = Lives.getInstance();
    assert(lives2.getLives() == 3);
  }

}

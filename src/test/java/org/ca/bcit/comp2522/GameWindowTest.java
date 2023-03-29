package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameWindowTest {


  private GameWindow gameWindow;

  @BeforeEach
  public void setUp() {
    gameWindow = new GameWindow();
    // Set up the PApplet instance
    PApplet.runSketch(new String[]{"GameWindowTest"}, gameWindow);
  }

  @Test
  public void testGetX() {
    assertEquals(800, GameWindow.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(600, GameWindow.getY());
  }

  @Test
  public void testSettings() {
    gameWindow.settings();
    assertEquals(800, gameWindow.width);
    assertEquals(600, gameWindow.height);
  }

  @Test
  public void testSetup() {
    gameWindow.setup();
    assertNotNull(gameWindow.getScene());
    assertNotNull(gameWindow.getAudio());
    assertNotNull(gameWindow.getInstructionStart());
    assertNotNull(gameWindow.getGameOver());
    assertNotNull(gameWindow.getGameVictory());
  }

  @Test
  public void testDraw() {
    gameWindow.setup();
    gameWindow.draw();
    assertNotNull(gameWindow.getScene());
    assertNotNull(gameWindow.getAudio());
    assertNotNull(gameWindow.getInstructionStart());
    assertNotNull(gameWindow.getGameOver());
    assertNotNull(gameWindow.getGameVictory());
  }

}

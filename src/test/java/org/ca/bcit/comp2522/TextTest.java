package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Bubble class creates the bubbles that the player can pop.
 * It extends Sprite.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
class TextTest {
  GameWindow gameWindow;
  Text text;
  PFont font;

  @BeforeEach
  public void setUp() {
    gameWindow = new GameWindow();
    // Set up the PApplet instance
    PApplet.runSketch(new String[]{"GameWindowTest"}, gameWindow);
    gameWindow.setup();
    font = gameWindow.createFont("../assets/PressStart2P-Regular.ttf", 18);
    text = new Text("Hello!", 200, 300, font);
  }

  @Test
  public void testConstructor() {
    assertEquals("Hello!", text.getText());
    assertEquals(200, text.getX());
    assertEquals(300, text.getY());
    assertEquals(font, text.getFont());
  }

}
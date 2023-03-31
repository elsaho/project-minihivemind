package org.ca.bcit.comp2522;

import processing.core.PConstants;
import processing.core.PFont;

/**
 * The main class for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Text {
  private final PFont font;
  private final String text;
  private final float x;
  private final float y;

  /**
   * Constructor for Text object.
   * @param text String for the actual text
   * @param x float positioning on screen
   * @param y float positioning on screen
   * @param font PFont
   */
  public Text(String text, float x, float y,PFont font){
    this.text = text;
    this.x = x;
    this.y = y;
    this.font = font;
  }

  /**
   * Displays text in window
   * @param window GameWindow where text is to be displayed.
   */
  public void display (final GameWindow window) {
    window.fill(255);
    window.textFont(font);
    window.textAlign(PConstants.LEFT);
    window.text(text, x, y);
  }
}

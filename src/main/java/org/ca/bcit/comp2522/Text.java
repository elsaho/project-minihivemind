package org.ca.bcit.comp2522;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;


/**
 * Text wrapper class for all in-game text needs.
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

  /** Getter for font stored */
  public PFont getFont() {
    return font;
  }

  /** Getter for text stored position */
  public String getText() {
    return text;
  }

  /** Getter for x position */
  public float getX() {
    return x;
  }

  /** Getter for y position */
  public float getY() {
    return y;
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

  public static void EndGameDisplay(GameWindow window, PImage bg, PFont myFont, DatabaseHelper databaseHelper, Button restart) {
    window.background(bg);
    window.textFont(myFont);
    String colName;
    if (GameLanding.getIs2P()) {
      colName = "score2P";
    } else {
      colName = "score1P";
    }
    Text highScoreText = new Text("High Score: "
        + databaseHelper.getHighestScore(colName, "score", Integer.class) + "\n"
        + "Your Score: " + ScoreBar.getInstance().getValue(), 20, 55, myFont);

    highScoreText.display(window);
    restart.display(window);
  }
}

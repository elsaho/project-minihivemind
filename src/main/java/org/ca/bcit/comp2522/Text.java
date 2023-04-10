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
public class Text implements Displayable {

  private final PFont font;
  private final String text;
  private final float textX;
  private final float textY;

  /**
   * Constructor for Text object.
   *
   * @param text String for the actual text
   * @param textX float positioning on screen
   * @param textY float positioning on screen
   * @param font PFont
   */
  public Text(String text, float textX, float textY, PFont font) {
    this.text = text;
    this.textX = textX;
    this.textY = textY;
    this.font = font;
  }

  /** Getter for font stored. */
  public PFont getFont() {
    return font;
  }

  /** Getter for text stored position. */
  public String getText() {
    return text;
  }

  /** Getter for x position. */
  public float getTextX() {
    return textX;
  }

  /** Getter for y position. */
  public float getTextY() {
    return textY;
  }


  /**
   * Displays text in window.
   *
   * @param window GameWindow where text is to be displayed.
   */
  public void display(final GameWindow window) {
    window.fill(255);
    window.textFont(font);
    window.textAlign(PConstants.LEFT);
    window.text(text, textX, textY);
  }

  /**
   * Launches end game display.
   *
   * @param window GameWindow
   * @param bg PImage
   * @param myFont PFont
   * @param databaseHelper DatabaseHelper
   * @param restart Button
   */
  public static void endGameDisplay(
      GameWindow window, PImage bg, PFont myFont, DatabaseHelper databaseHelper, Button restart) {
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

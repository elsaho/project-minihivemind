package org.ca.bcit.comp2522;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Manages game over screen.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class GameOver{

  /**
   * Properties
   */
  private PImage bg;
  private PImage replayButton;
  private PFont myFont;
  private Button restart;
  private DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  /**
   * Constructor
   * @param window GameWindow
   */
  public GameOver(GameWindow window) {

  }

  /**
   * Sets up the game over page
   * @param window GameWindow
   */
  public void setup(GameWindow window) {
    bg = window.loadImage("../assets/GameOverScreen.png");
    replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 323, 600, 206, replayButton);
    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
  }

  /**
   * Displays the game over page
   *
   * @param window GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    window.textSize(32); // Set text size to 16 pixels
    window.textAlign(PConstants.LEFT);
    window.textFont(myFont);
    window.text("High Score: " + databaseHelper.getHighestScore() + "\n"
            + "Your Score: " +databaseHelper.getPlayerScore(), 20, 55);
    restart.display(window);
  }

  /**
   * Updates the game over page
   *
   * @param window GameWindow
   */
  public void update(GameWindow window) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Restart button clicked!");
      window.setup();
    }
  }

}
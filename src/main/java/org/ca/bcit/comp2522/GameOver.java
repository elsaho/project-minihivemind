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
    window.EndGameDisplay(window, bg, myFont, databaseHelper, restart);
  }

  /**
   * Updates the game over page
   *
   * @param window GameWindow
   */
  public void update(GameWindow window) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Restart button clicked!");
      Lives lives = Lives.getInstance();
      lives.setLives(3);
      window.setup();
    }
  }

}
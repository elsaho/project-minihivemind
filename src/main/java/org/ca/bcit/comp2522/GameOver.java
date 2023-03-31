package org.ca.bcit.comp2522;

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
  private final PImage bg;
  private PImage replayButton;
  private final PFont myFont;
  private final Button restart;
  private DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
  private Scene scene;

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
    Text.EndGameDisplay(window, bg, myFont, databaseHelper, restart);
  }


  /**
   * Updates the game over page
   *
   * @param window GameWindow
   */
  public void update(GameWindow window) {
    Button.restartGame(window, restart);
  }

}
package org.ca.bcit.comp2522;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Manages game victory page for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class GameVictory {
  /**
   * Properties
   */
  private PImage bg;
  private PImage replayButton;
  private PFont myFont;
  private Button restart;
  private DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  /**
   * Constructs the game victory page
   * @param window as a GameWindow
   */
  public GameVictory(GameWindow window) {
    bg = window.loadImage("../assets/VictoryScreen.png");
    replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 280, 600, 206, replayButton);
    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
  }

  /**
   * Displays the victory page
   * @param window as a Game Window
   */
  public void display(GameWindow window) {
    GameWindow.EndGameDisplay(window, bg, myFont, databaseHelper, restart);
  }

  /**
   * Updates the victory page
   * @param window as a GameWindow
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

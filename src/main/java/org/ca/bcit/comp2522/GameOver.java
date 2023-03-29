package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * GameOver. The class for the game over screen.
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
  private Button restart;

  /**
   * Constructor
   * @param window
   */
  public GameOver(GameWindow window) {

  }

  /**
   * Sets up the game over page
   * @param window
   */
  public void setup(GameWindow window) {
    bg = window.loadImage("../assets/GameOverScreen.png");
    replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 323, 600, 206, replayButton);
  }

  /**
   * Displays the game over page
   * @param window
   */
  public void display(GameWindow window) {
    window.background(bg);
    restart.display(window);
  }

  /**
   * Updates the game over page
   * @param window
   */
  public void update(GameWindow window) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Restart button clicked!");
      window.setup();
    }
  }

  /**
   * Draws the game over page
   */
  public void draw(GameWindow window) {
    window.background(bg);
    restart.display(window);
  }
}
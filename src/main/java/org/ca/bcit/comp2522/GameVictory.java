package org.ca.bcit.comp2522;

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
  private Button restart;

  /**
   * Constructs the game victory page
   * @param window as a GameWindow
   */
  public GameVictory(GameWindow window) {
  }

  /**
   * Sets up the game victory page
   * @param window as a GameWindow
   */
  public void setup(GameWindow window) {
    bg = window.loadImage("../assets/VictoryScreen.png");
    replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 280, 600, 206, replayButton);
  }

  /**
   * Displays the victory page
   * @param window as a Game Window
   */
  public void display(GameWindow window) {
    window.background(bg);
    restart.display(window);
  }

  /**
   * Updates the victory page
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Restart button clicked!");
      window.setup();
    }
  }

  /**
   * Draws the victory page
   * @param window as a GameWindow
   */

  //Commented out for now as it is not being used, consider for deletion
  //  public void draw(GameWindow window) {
  //    window.background(bg);
  //    restart.display(window);
  //  }
}

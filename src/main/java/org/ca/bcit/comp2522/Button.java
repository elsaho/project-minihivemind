package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Button. Makes clickable buttons for game screens
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Button{

  /**
   * Button properties
   */
  private int xPos;
  private int yPos;
  private int width;
  private int height;
  private PImage img;

  /**
   * Makes a button
   * @param xPos as an int
   * @param yPos as an int
   * @param width as an int
   * @param height as an int
   * @param img as a PImage
   */
  public Button(int xPos, int yPos, int width, int height, PImage img) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.img = img;
  }

  /**
   * Checks if button is clicked
   * @param mouseX as a float
   * @param mouseY as a float
   * @param mousePressed as a boolean
   * @return boolean
   */
  public boolean isClicked(float mouseX, float mouseY, boolean mousePressed) {
    return mousePressed &&
            mouseX >= xPos &&
            mouseX <= xPos + width &&
            mouseY >= yPos &&
            mouseY <= yPos + height;
  }

  /**
   * Displays buttons onto the window
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.image(img, xPos, yPos, width, height);
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.fill(0);
  }

  /**
   * Restarts the game
   *
   * @param window as a GameWindow
   * @param restart as a Button
   */
  public static void restartGame(GameWindow window, Button restart) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameManager.gameReset(window);
      GameWindow.screen = Screen.level1;
      window.init();
    }
  }


}

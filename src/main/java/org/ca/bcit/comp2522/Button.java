package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Button. Makes clickable buttons for game screens
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Button {

  /**
   * Button properties.
   */
  private final int xpos;
  private final int ypos;
  private final int width;
  private final int height;
  private final PImage img;

  /**
   * Makes a button.
   *
   * @param xpos as an int
   * @param ypos as an int
   * @param width as an int
   * @param height as an int
   * @param img as a PImage
   */
  public Button(int xpos, int ypos, int width, int height, PImage img) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.width = width;
    this.height = height;
    this.img = img;
  }

  /**
   * Checks if button is clicked.
   *
   * @param mouseX as a float
   * @param mouseY as a float
   * @param mousePressed as a boolean
   * @return boolean
   */
  public boolean isClicked(float mouseX, float mouseY, boolean mousePressed) {
    return mousePressed && mouseX >= xpos && mouseX <= xpos + width
        && mouseY >= ypos && mouseY <= ypos + height;
  }

  /**
   * Displays buttons onto the window.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.image(img, xpos, ypos, width, height);
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.fill(0);
  }

  /**
   * Restarts the game.
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

  /**
   * Selects the number of players.
   *
   * @param window as a GameWindow
   * @param onePlayerBtn as a Button
   * @param twoPlayerBtn as a Button
   */
  public static void selectMultiPlayer(GameWindow window, Button onePlayerBtn, Button twoPlayerBtn) {
    if (onePlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameLanding.setIs2P(false);
      window.init();
      System.out.println("1P");
      GameWindow.screen = Screen.level1;
    } else if (twoPlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameLanding.setIs2P(true);
      window.init();
      System.out.println("2P");
      GameWindow.screen = Screen.level1;
    }
  }
}

package org.ca.bcit.comp2522;

import processing.core.PImage;

/** Manages the landing page.
 *
 */
public class GameLanding extends GameScreen{

  private final PImage bg;
  private final Button instructBtn;
  private final Button onePlayerBtn;
  private final Button twoPlayerBtn;
  private static boolean is2P = false;

  /** Constructor for GameLanding.
   *
   * @param window as a GameWindow
   */
  public GameLanding(GameWindow window) {
    bg = window.loadImage("../assets/Landing.png");
    PImage onePlayerImg = window.loadImage("../assets/1PlayerBtn.png");
    PImage twoPlayerImg = window.loadImage("../assets/2PlayerBtn.png");
    PImage instructBtnImg = window.loadImage("../assets/redInstructBtn.png");
    onePlayerBtn = new Button(144, 381, 125, 125, onePlayerImg);
    twoPlayerBtn = new Button(338, 381, 125, 125, twoPlayerImg);
    instructBtn = new Button(532, 381, 125, 125, instructBtnImg);
  }

  /**
   * Displays the landing page.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    onePlayerBtn.display(window);
    twoPlayerBtn.display(window);
    instructBtn.display(window);
  }

  /**
   * Updates the landing page.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    GameManager.gameReset(window);
    if (instructBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.instruction;
      window.init();
    } else {
      Button.selectMultiPlayer(window, onePlayerBtn, twoPlayerBtn);
    }
  }

  public static boolean getIs2P() {
    return is2P;
  }

  public static void setIs2P(boolean b) {
    is2P = b;
  }
}



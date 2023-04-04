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
    bg = window.loadImage("../assets/GraphicLanding.png");
//    PImage startButtonImg = window.loadImage("../assets/playBtn.png");
    PImage onePlayerImg = window.loadImage("../assets/1PlayerBtn.png");
    PImage twoPlayerImg = window.loadImage("../assets/2PlayerBtn.png");
    PImage instructBtnImg = window.loadImage("../assets/redInstructBtn.png");
    onePlayerBtn = new Button(144, 360, 125, 125, onePlayerImg);
    twoPlayerBtn = new Button(338, 360, 125, 125, twoPlayerImg);
//    startGameBtn = new Button(169, 381, 125, 125, startButtonImg);
    instructBtn = new Button(532, 360, 125, 125, instructBtnImg);
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
    if (onePlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      is2P = false;
      window.init();
      GameWindow.screen = Screen.level1;
    } else if (twoPlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      is2P = true;
      window.init();
      GameWindow.screen = Screen.level1;
    } else if (instructBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.instruction;
      System.out.println("instruction button clicked");
      window.init();
    }
  }

  public static boolean getIs2P() {
    return is2P;
  }

  public static void setIs2P(boolean b) {
    is2P = b;
  }
}



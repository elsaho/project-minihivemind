package org.ca.bcit.comp2522;

import processing.core.PImage;

/** Manages the landing page.
 *
 */
public class GameLanding extends GameScreen{

  private final PImage bg;
  private final Button startGameBtn;
  private final Button instructBtn;

  /** Constructor for GameLanding.
   *
   * @param window as a GameWindow
   */
  public GameLanding(GameWindow window) {
    bg = window.loadImage("../assets/Landing.png");
    PImage startButtonImg = window.loadImage("../assets/playBtn.png");
    PImage instructBtnImg = window.loadImage("../assets/instructBtn.png");
    startGameBtn = new Button(169, 381, 125, 125, startButtonImg);
    instructBtn = new Button(507, 381, 125, 125, instructBtnImg);
  }

  /**
   * Displays the landing page.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    startGameBtn.display(window);
    instructBtn.display(window);
  }

  /**
   * Updates the landing page.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    GameManager.gameReset(window);
    if (startGameBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.playerSelect;
      System.out.println("start button clicked");
      window.init();
    } else if (instructBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.instruction;
      System.out.println("instruction button clicked");
      window.init();
    }
  }


}

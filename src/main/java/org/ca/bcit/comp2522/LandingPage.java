package org.ca.bcit.comp2522;

import processing.core.PImage;

public class LandingPage {

  private final PImage bg;
  private final Button startGameBtn;
  private final Button instructBtn;
  public static boolean gameStarted = false;

  public LandingPage(GameWindow window) {
    bg = window.loadImage("../assets/Landing.png");
    PImage startButtonImg = window.loadImage("../assets/playBtn.png");
    PImage instructBtnImg = window.loadImage("../assets/instructBtn.png");
    startGameBtn = new Button(169, 381, 125, 125, startButtonImg);
    instructBtn = new Button(507, 381, 125, 125, instructBtnImg);
  }

  /**
   * Displays the landing page
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    startGameBtn.display(window);
    instructBtn.display(window);
  }

  /**
   * Updates the landing page
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    if (startGameBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Start Game button clicked!");
      gameStarted = true;
      window.setup();
    }
    if (instructBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Instruct button clicked! Doesn't do anything yet");
    }
  }


}

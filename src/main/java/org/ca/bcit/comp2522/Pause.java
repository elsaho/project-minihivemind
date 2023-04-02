package org.ca.bcit.comp2522;

import java.io.FileNotFoundException;
import javax.sound.sampled.LineUnavailableException;
import processing.core.PFont;
import processing.core.PImage;

/** Pause class. The class that manages the pause screen.
 */
public class Pause {
  /**
   * Properties.
   */
  private final PImage bg;

  private final Button resume;
  private final Button home;
  private final Text text;

  /** Constructor for Pause.
   *
   * @param window as a GameWindow
   */
  public Pause(GameWindow window) {
    bg = window.loadImage("../assets/SkyBackground.png");
    PImage playImg = window.loadImage("../assets/playBtn.png");
    PImage homeImg = window.loadImage("../assets/homeBtn.png");
    resume = new Button(169, 281, 125, 125, playImg);
    home = new Button(507, 281, 125, 125, homeImg);
    PFont myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
    text = new Text("Game is Paused...", 160, 130, myFont);
  }

  /**
   * Displays the pause page.
   *
   * @param window as a GameWindowq
   */
  public void display(GameWindow window) {
    window.background(bg);
    resume.display(window);
    home.display(window);
    text.display(window);
  }

  /**
   * Updates the select pause screen.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) throws LineUnavailableException, FileNotFoundException {
    if (resume.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.level1;
      window.init();
    } else if (home.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.landing;
      GameManager.gameReset(window);
      window.init();
    }
  }
}

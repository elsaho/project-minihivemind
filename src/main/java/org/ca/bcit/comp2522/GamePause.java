package org.ca.bcit.comp2522;

import java.io.FileNotFoundException;
import javax.sound.sampled.LineUnavailableException;
import processing.core.PFont;
import processing.core.PImage;

/** GamePause class. The class that manages the pause screen.
 */
public class GamePause extends GameScreen {
  /**
   * Properties.
   */
  private final PImage bg;
  private final Button resume;
  private final Button home;
  private final Button restart;
  private final Text text;

  /** Constructor for GamePause.
   *
   * @param window as a GameWindow
   */
  public GamePause(GameWindow window) {
    bg = window.loadImage("../assets/SkyBackground.png");
    PImage playImg = window.loadImage("../assets/playBtn.png");
    PImage homeImg = window.loadImage("../assets/homeBtn.png");
    PImage restartImg = window.loadImage("../assets/restartBtn.png");
    resume = new Button(144, 281, 125, 125, playImg);
    home = new Button(532, 281, 125, 125, homeImg);
    restart = new Button(338, 281, 125, 125, restartImg);
    PFont myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
    text = new Text("Game is Paused...", 160, 130, myFont);
  }

  /**
   * Displays the pause page.
   *
   * @param window as a GameWindowq
   */
  @Override
  public void display(GameWindow window) {
    window.background(bg);
    resume.display(window);
    home.display(window);
    restart.display(window);
    text.display(window);
  }

  /**
   * Updates the select pause screen.
   *
   * @param window as a GameWindow
   */
  @Override
  public void screenUpdate(GameWindow window)
          throws LineUnavailableException, FileNotFoundException {
    if (resume.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameWindow.screen = Screen.level1;
      window.init();
      Scene.isPaused = false;
    } else if (home.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      Scene.isPaused = false;
      GameWindow.screen = Screen.landing;
      window.init();
    } else if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      Scene.isPaused = false;
      GameWindow.screen = Screen.level1;
      window.init();
    }
  }
}

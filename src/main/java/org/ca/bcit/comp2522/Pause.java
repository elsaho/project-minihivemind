package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;

public class Pause {
  /**
   * Properties
   */
  private final PImage bg;
  private PImage playImg;
  private PImage homeImg;
  private final PFont myFont;
  private final Button resume;
  private final Button home;
  private Text text;
//  private static boolean is2P = false;

  public Pause(GameWindow window) {
    bg = window.loadImage("../assets/SkyBackground.png");
    playImg = window.loadImage("../assets/playBtn.png");
    homeImg = window.loadImage("../assets/home.png");
    resume = new Button(169, 281, 125, 125, playImg);
    home = new Button(507, 281, 125, 125, homeImg);
    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
    text = new Text("Pause", 260, 120, myFont);
  }

//  public static boolean getIs2P() {
//    return is2P;
//  }

  /**
   * Displays the select multiplayer page
   *
   * @param window as a GameWindow
   * @param scene
   */
  public void display(GameWindow window, Scene scene) {
    window.background(bg);
    resume.display(window);
    home.display(window);
    text.display(window);
  }

  /**
   * Updates the select pause screen
   *
   * @param window as a GameWindow
   * @param scene as a Scene
   */
  public void update(GameWindow window, Scene scene) throws LineUnavailableException, FileNotFoundException {
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

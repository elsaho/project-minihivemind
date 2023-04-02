package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

/**
 * Manages select multiplayer screen
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class SelectMultiPlayer {

  /**
   * Properties
   */
  private final PImage bg;
  private PImage onePlayerImg;
  private PImage twoPlayerImg;
  private final PFont myFont;
  private final Button onePlayerBtn;
  private final Button twoPlayerBtn;
  private Text text;

  public SelectMultiPlayer(GameWindow window) {
    bg = window.loadImage("../assets/SkyBackground.png");
    onePlayerImg = window.loadImage("../assets/1PlayerBtn.png");
    twoPlayerImg = window.loadImage("../assets/2PlayerBtn.png");
    onePlayerBtn = new Button(169, 381, 125, 125, onePlayerImg);
    twoPlayerBtn = new Button(507, 381, 125, 125, twoPlayerImg);
    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
    text = new Text("1p or 2p?", 100, 100, myFont);
  }

  /**
   * Displays the select multiplayer page
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    onePlayerBtn.display(window);
    twoPlayerBtn.display(window);
    text.display(window);
  }

  /**
   * Updates the select multiplayer page
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    if (onePlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("One player button was clicked!");
      // do stuff
    } else if (twoPlayerBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Two player button was clicked!");
      // do stuff
    }
  }

}
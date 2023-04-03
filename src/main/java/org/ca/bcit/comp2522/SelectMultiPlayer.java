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
  private final Button onePlayerBtn;
  private final Button twoPlayerBtn;
  private final Text text;

  public SelectMultiPlayer(GameWindow window) {
    bg = window.loadImage("../assets/SkyBackground.png");
    PImage onePlayerImg = window.loadImage("../assets/1PlayerBtn.png");
    PImage twoPlayerImg = window.loadImage("../assets/2PlayerBtn.png");
    onePlayerBtn = new Button(169, 325, 125, 125, onePlayerImg);
    twoPlayerBtn = new Button(507, 325, 125, 125, twoPlayerImg);
    PFont myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
    text = new Text("1p or 2p?", 260, 120, myFont);
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
    GameManager.gameReset(window);
    Button.selectMultiPlayer(window, onePlayerBtn, twoPlayerBtn);
  }

}
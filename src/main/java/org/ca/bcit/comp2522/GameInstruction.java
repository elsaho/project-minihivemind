package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

/** Manages instruction start screen.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class GameInstruction extends GameScreen {
  /** Image imports: background. */
  private final PImage bg;
  /** Start button function button. */
  private final Button onePlayerBtn;
  private final Button twoPlayerBtn;
  private final Text text;


  /**
   * Constructor for GameInstruction.
   *
   * @param window as a GameWindow
   */
  public GameInstruction(GameWindow window) {
    bg = window.loadImage("../assets/newInstruct.png");
    PImage onePlayerImg = window.loadImage("../assets/1PlayerBtn.png");
    PImage twoPlayerImg = window.loadImage("../assets/2PlayerBtn.png");
    onePlayerBtn = new Button(150, 400, 125, 125, onePlayerImg);
    twoPlayerBtn = new Button(550, 400, 125, 125, twoPlayerImg);
    PFont myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 30);
    text = new Text("Pop the Bubbles!", 180, 85, myFont);
  }

  /**
   * Displays the instruction start page.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    onePlayerBtn.display(window);
    twoPlayerBtn.display(window);
    text.display(window);
  }

  /**
   * Updates the instruction start page.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    Button.selectMultiPlayer(window, onePlayerBtn, twoPlayerBtn);
  }
}

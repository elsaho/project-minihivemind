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
   * Getter for BG image, setup for test.
   * @return bg PImage
   */
  public PImage getBg() {
    return bg;
  }

  /**
   * Getter for P1 Button, setup for test.
   * @return onePlayerBtn Button
   */
  public Button getOnePlayerBtn() {
    return onePlayerBtn;
  }

  /**
   * Getter for P2 Button, setup for test.
   * @return twoPlayerBtn Button
   */
  public Button getTwoPlayerBtn() {
    return twoPlayerBtn;
  }

  /**
   * Getter for text, setup for test.
   * @return text Text
   */
  public Text getText() {
    return text;
  }

  /**
   * Displays the instruction start page.
   *
   * @param window as a GameWindow
   */
  @Override
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
  @Override
  public void screenUpdate(GameWindow window) {
    Button.selectMultiPlayer(window, onePlayerBtn, twoPlayerBtn);
  }
}

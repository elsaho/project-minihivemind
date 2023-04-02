package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

/** Manages instruction start screen.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class InstructionStart {
  /** Image imports: background. */
  private final PImage bg;
  /** Start button function button. */
  private final Button startGameBtn;
  private final Text text;


  /**
   * Constructor for InstructionStart.
   *
   * @param window as a GameWindow
   */
  public InstructionStart(GameWindow window) {
    bg = window.loadImage("../assets/newInstruct.png");
    PImage startButtonImg = window.loadImage("../assets/newStart.png");
    startGameBtn = new Button(195, 400, 400, 138, startButtonImg);
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
    startGameBtn.display(window);
    text.display(window);
  }

  /**
   * Updates the instruction start page.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    if (startGameBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      GameManager.gameReset(window);
      System.out.println("Select player button clicked!");
      GameWindow.screen = Screen.playerSelect;
    }
  }
}

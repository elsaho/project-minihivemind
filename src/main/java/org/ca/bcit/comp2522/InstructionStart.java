package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class InstructionStart{
  /** If player has seen the instruction screen */
  public static boolean gameStarted;
  /** Image imports: background */
  private PImage bg;
  /** Start button function button */
  private Button startGameBtn;

  /**
   * Constructor for InstructionStart
   * @param window as a GameWindow
   */
  public InstructionStart(GameWindow window) {
    gameStarted = false;
    bg = window.loadImage("../assets/SkyInstruction.png");
    PImage startButtonImg = window.loadImage("../assets/StartButton.png");
    startGameBtn = new Button(100, 323, 600, 206, startButtonImg);
  }

  /**
   * Displays the instruction start page
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    startGameBtn.display(window);
  }

  /**
   * Updates the instruction start page
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    if (startGameBtn.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Start Game button clicked!");
      gameStarted = true;
    }
  }

  /**
   * Draws the instruction start page
   * @param window as a GameWindow
   */
  public void draw(GameWindow window) {
    window.background(bg);
    startGameBtn.display(window);
  }
}

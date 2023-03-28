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
   * Constructor
   * @param window GameWindow main window of game
   */
  public InstructionStart(GameWindow window) {
    gameStarted = false;
  }

  /**
   * Sets up the game over page
   * @param parent PApplet refers to GameWindow
   */
  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/SkyInstruction.png");
    PImage startButtonImg = parent.loadImage("../assets/StartButton.png");
    startGameBtn = new Button(100, 323, 600, 206, startButtonImg);
  }

  /**
   * Displays the game over page
   * @param parent PApplet refers to GameWindow
   */
  public void display(PApplet parent) {
    parent.background(bg);
    startGameBtn.display(parent);
  }

  /**
   * Updates the game over page
   * @param parent PApplet refers to GameWindow
   */
  public void update(PApplet parent) {
    if (startGameBtn.isClicked(parent.mouseX, parent.mouseY, parent.mousePressed)) {
      System.out.println("Start Game button clicked!");
      gameStarted = true;
    }
  }

  /**
   * Draws the game over page
   */
  public void draw(PApplet parent) {
    parent.background(bg);
    startGameBtn.display(parent);
  }
}

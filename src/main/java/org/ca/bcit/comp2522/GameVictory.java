package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

/**
 * Manages game victory page for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class GameVictory extends GameScreen {
  /**
   * Properties.
   */
  private final PImage bg;
  private final PFont myFont;
  private final Button restart;
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  /**
   * Constructs the game victory page.
   *
   * @param window as a GameWindow
   */
  public GameVictory(GameWindow window) {
    bg = window.loadImage("../assets/VictoryScreen.png");
    PImage replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 280, 600, 206, replayButton);
    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 32);
  }

  /**
   * Displays the victory page.
   *
   * @param window as a Game Window
   */
  @Override
  public void display(GameWindow window) {
    Text.endGameDisplay(window, bg, myFont, databaseHelper, restart);
  }

  /**
   * Updates the victory page.
   *
   * @param window as a GameWindow
   */
  @Override
  public void screenUpdate(GameWindow window) {
    Button.restartGame(window, restart);
  }


}

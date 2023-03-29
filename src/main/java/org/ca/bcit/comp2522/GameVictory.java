package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class GameVictory {
  /**
   * Properties
   */
  private PImage bg;
  private PImage replayButton;
  private Button restart;

  /**
   * Constructs the game victory page
   * @param window
   */
  public GameVictory(GameWindow window) {
  }

  public void setup(GameWindow window) {
    bg = window.loadImage("../assets/VictoryScreen.png");
    replayButton = window.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 280, 600, 206, replayButton);
  }

  public void display(GameWindow window) {
    window.background(bg);
    restart.display(window);
  }


  public void update(GameWindow window) {
    if (restart.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      System.out.println("Restart button clicked!");
      window.setup();
    }
  }

  public void draw(GameWindow window) {
    window.background(bg);
    restart.display(window);
  }
}

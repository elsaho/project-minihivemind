package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class GameVictory extends PApplet {
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

  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/VictoryScreen.png");
    replayButton = parent.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 280, 600, 206, replayButton);
  }

  public void display(PApplet parent) {
    parent.background(bg);
    restart.display(parent);
  }


  public void update(PApplet parent) {
    if (restart.isClicked(parent.mouseX, parent.mouseY, parent.mousePressed)) {
      System.out.println("Restart button clicked!");
      parent.setup();
    }
  }

  public void draw() {
    background(bg);
    restart.display(this);
  }
}

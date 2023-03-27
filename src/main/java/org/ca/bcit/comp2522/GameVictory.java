package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class GameVictory extends PApplet {
  /**
   * Properties
   */
  private PImage bg;

  /**
   * Constructs the game victory page
   * @param window
   */
  public GameVictory(GameWindow window) {
  }

  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/SkyBackground.png");
  }

  public void display(PApplet parent) {
    parent.background(bg);
  }


  public void update(PApplet parent) {
  }

  public void draw() {
    background(bg);
  }
}

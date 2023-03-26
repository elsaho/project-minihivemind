package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

public class GameOver extends PApplet{

  private PImage bg;

  public GameOver(GameWindow window) {

  }

  public void setup(PApplet parent) {
    super.setup();
    bg = parent.loadImage("../assets/NightBackground.png");
    parent.registerMethod("mousePressed", this);
  }


  public void display(PApplet parent) {
    parent.background(bg);
  }

  public void update(PApplet parent) {
  }

  public void draw() {
  }

  public void mousePressed(MouseEvent event) {
    System.out.println("Mouse pressed in game over");
    if (mouseButton == LEFT) {
      System.out.println("Left mouse button clicked");
    }
  }

}
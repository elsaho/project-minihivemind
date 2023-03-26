package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

public class GameOver extends PApplet{

  private PImage bg;
  private Button restart;

  public GameOver(GameWindow window) {

  }

  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/NightBackground.png");
    restart = new Button(100, 100, 100, 50, "Restart");
//    noLoop();
  }

  public void display(PApplet parent) {
    parent.background(bg);
    restart.display(parent);
  }


  public void update(PApplet parent) {
    if (restart.isClicked(parent.mouseX, parent.mouseY, parent.mousePressed)) {
      System.out.println("Restart button clicked!");
    }
  }


  public void draw() {
    background(bg);
    restart.display(this);
  }

//  public void mouseClicked() {
//    System.out.println("Restart button clicked!");
//  }

}
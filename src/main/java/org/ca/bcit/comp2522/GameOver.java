package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class GameOver extends PApplet{

  private PImage bg;

  public GameOver(GameWindow window) {

  }

  public void setup(PApplet parent) {
    super.setup();
    bg = parent.loadImage("../assets/NightBackground.png");
  }

  public void display(PApplet parent) {
    parent.background(bg);
  }

  public void update(PApplet parent) {
  }

  public void draw() {
  }

  public void mousePressed() {
  }

}

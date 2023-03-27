package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class StartPage extends PApplet {

  private PImage bg;

  public StartPage(GameWindow window) {
  }

  public void setup(PApplet parent) {
    super.setup();
    bg = parent.loadImage("../assets/SkyBackground.png");
  }

  public void display(PApplet parent) {
    parent.background(bg);
  }

  public void update(PApplet parent) {
  }

  public void draw() {
  }
}

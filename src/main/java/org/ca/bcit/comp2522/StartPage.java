package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class StartPage extends PApplet {

  private PImage bg;
  private PImage title;

  public StartPage(GameWindow window) {
  }

  public void setup(PApplet parent) {
    super.setup();
    bg = parent.loadImage("../assets/SkyBackground.png");
    title = parent.loadImage("../assets/title.png");
  }

  public void display(PApplet parent) {
    parent.background(bg);
  }

  public void update(PApplet parent) {
  }

  public void draw() {
    image(title, 50, 50);
  }
}

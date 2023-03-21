package org.ca.bcit.comp2522;

import processing.core.PApplet;

public class Scene {
  private PApplet parent;

  public Scene() {

  }

  public void setParent(PApplet parent) {
    this.parent = parent;
  }

  public void display() {
    parent.fill(255, 0, 0);
    parent.rect(100, 100, 200, 50);
  }

  public void update() {

  }
}

package org.ca.bcit.comp2522;
import processing.core.PVector;

import processing.core.PApplet;
import processing.core.PVector;

public class Sprite {
  private PVector position;
  private PVector velocity;
  private float size;
  private int color;

  public Sprite(float x, float y, float size, int color) {
    position = new PVector(x, y);
    velocity = new PVector(0, 0);
    this.size = size;
    this.color = color;
  }

  public void update() {
    position.add(velocity);
  }

  public void display(PApplet parent) {
    parent.fill(color);
    parent.ellipse(position.x, position.y, size, size);
  }

  public void setVelocity(float x, float y) {
    velocity = new PVector(x, y);
  }
}


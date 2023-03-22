package org.ca.bcit.comp2522;

import processing.core.PApplet;

public class Player {
  private float x;
  private float y;
  private float width;
  private float height;
  private float speed;

  public Player(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.speed = 5;
  }

  public void display(PApplet parent) {
    parent.fill(0, 255, 0);
    parent.rect(x, y, width, height);
  }

  public void update(PApplet parent) {
    if (parent.keyPressed) {
      if (parent.key == 'a') {
        x -= speed;
      } else if (parent.key == 'd') {
        x += speed;
      }
    }
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }
}


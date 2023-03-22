package org.ca.bcit.comp2522;

import processing.core.PApplet;

public class Player {

  private float x;
  private float y;
  private float width;
  private float height;
  private float speed;

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


  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

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
        if (x - speed < 0) {
          x = 0;
        }
        x -= speed;
      } else if (parent.key == 'd') {
        if (x + speed > GameWindow.getX() - 50) {
          x = GameWindow.getX() - 50;
        }
        x += speed;
      }
    }
  }
}


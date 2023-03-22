package org.ca.bcit.comp2522;
import processing.core.PVector;

import processing.core.PApplet;
import java.awt.*;

public class Sprite implements Comparable<Sprite>, Drawable, Collidable {
  protected PVector position;
  protected PVector direction;
  protected float size;
  protected float speed;
  protected Color color;
  protected Scene scene;

  public boolean collided() {
    return false;
  }


  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Scene scene) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.scene = scene;
    this.color = color;
  }

  public void bounce() {
    if (this.position.x <= 0 ||
        this.position.x >= scene.width ||
        this.position.y <= 0 ||
        this.position.y >= scene.height) {
      this.direction.rotate(scene.HALF_PI);
    }
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public PVector getPosition() {
    return position.copy();
  }

  public void display(PApplet parent) {
    parent.fill(0, 255, 0);
    parent.rect(this.position.x, this.position.y, 10, 10);
  }

  public void update() {
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public float getSize() {
    return size;
  }

  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    if (distance <= (a.getSize() + b.getSize())) {
      return true;
    }
    return false;
  }

  public void draw() {
    scene.pushStyle();
    scene.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    scene.ellipse(this.position.x, this.position.y, size, size);
    scene.popStyle();
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sprite sprite = (Sprite) o;
    return Float.compare(sprite.size, size) == 0;
  }

  @Override
  public int compareTo(Sprite sprite) {
    int value = 0;
    if ((this.getSize() - sprite.getSize()) > 0) {
      value = 1;
    } else {
      value = -1;
    }
    return value;
  }
}


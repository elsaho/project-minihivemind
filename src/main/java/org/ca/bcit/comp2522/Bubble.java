package org.ca.bcit.comp2522;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


/**
 * Bubble. Creates the bubbles that the player can pop.
 * Extends Sprite and implements Poppable.
 *
 * @author Elsa Ho
 * @version 2023
 *
 */
public class Bubble extends Sprite implements Poppable {
  private static final float GRAVITY = 0.1f;
  public PVector velocity;
  public Line line;
  private PImage bubbleImage;

  private float startY;
  /**
   * Constructor for Bubble.
   *
   * @param position as a PVector
   * @param direction as a PVector
   * @param size as a float
   * @param speed as a float
   * @param color as a Color
   * @param window as a GameWindow
   * @param velocity as a PVector
   */
  public Bubble(PVector position, PVector direction, float size,
                float speed, Color color, GameWindow window, PVector velocity) {

    super(position, direction, size, speed, color, window);
    this.velocity = velocity;
    bubbleImage = window.loadImage("../assets/bubble.png");
    this.startY = GameWindow.getY() - (position.y + size/2);
  }

  /**
   * Helper method to multiply vectors.
   *
   * @param a as a PVector
   * @param b as a PVector
   * @return PVector
   */
  public PVector multVector(PVector a, PVector b) {
    PVector result = new PVector(0, 0);
    result.x = a.x * b.x;
    result.y = a.y * b.y;
    return result;
  }

  /**
   * Bounce method that allows bubbles to bounce off floors and walls.
   */
  private float currentX = size;

  public void bounce() {

    if (position.y + velocity.y > GameWindow.getY() - size) {
      position.y = GameWindow.getY() - size;
      velocity.y = -velocity.y;
    } else if (position.y + velocity.y < startY) {
      position.y = startY;
      velocity.y = -velocity.y;
    }

    if (position.x + velocity.x > GameWindow.getX() - (size / 2)) {
      position.x = GameWindow.getX() - (size / 2);
      velocity.x = -velocity.x;
      currentX = position.x - size;
    } else if (position.x + velocity.x < 0) {
      position.x = 0;
      velocity.x = -velocity.x;
    } else {
      // Add a small amount to the x position
      if (currentX == size) {
        position.x += 2;
        if (position.x == GameWindow.getX() - size) {
          currentX = position.x;
        }
      } else {
        if (position.x <= 0) {
          currentX = size;
        }
        position.x -= 2;
      }
    }

    //bounce off the floor
    if (position.y + size / 2 >= GameWindow.getY()) {
      velocity.y = -velocity.y;
    }
    velocity.y += GRAVITY;
    position.add(velocity);
  }

  @Override
  public void pop() {
  }


  @Override
  public boolean collided() {
    return false;
  }

  @Override
  public void display(PApplet parent) {
    parent.pushStyle();
    parent.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    parent.image(bubbleImage, this.position.x, this.position.y, size, size);
    parent.popStyle();
  }
}

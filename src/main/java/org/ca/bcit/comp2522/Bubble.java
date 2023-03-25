package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Bubble extends Sprite implements Poppable {
  private PVector velocity;

  public Bubble(PVector position, PVector direction, float size, float speed, Color color, Scene scene, PVector velocity) {
    super(position, direction, size, speed, color, scene);
    this.velocity = velocity;
  }

  /**
   * Helper method to multiply vectors
   * @param a as a PVector
   * @param b as a PVector
   * @return PVector
   */
  public PVector multVector(PVector a, PVector b) {
    PVector result = new PVector (0, 0);
    result.x = a.x * b.x;
    result.y = a.y * b.y;
    return result;
  }

  /**
   * Bounce method that allows bubbles to bounce off floors and walls
   */
  @Override
  public void bounce() {
    if (position.y + velocity.y < GameWindow.getY() - (size/2) &&
            position.y + velocity.y > 0 + (size/2) &&
            position.x + velocity.x < GameWindow.getX() - (size/2) &&
            position.x + velocity.x > 0 + (size/2)) {
      position = position.add(velocity);
    } else if (position.y + velocity.y >= GameWindow.getY() - (size/2) ||
            position.y + velocity.y <= 0 + (size/2)) {

      if (velocity.x != 0) {
        velocity = multVector(velocity, new PVector(1, -1));
      } else {
        velocity.x = velocity.y; //initialize
        velocity = multVector(velocity, new PVector(1, -1));
      }
    } else if (position.x + velocity.x >= GameWindow.getX() - (size/2) ||
            position.x + velocity.x <= 0 + (size/2)) {
      velocity = multVector(velocity, new PVector(-1, -1));
    }
  }

  @Override
  public void pop() {
    //TODO
  }
}

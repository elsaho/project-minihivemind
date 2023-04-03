package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

/**
 * ShootLine. The class that creates the shootLine that the player can use to catch the bubbles.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class ShootLine {
  private PVector position;
  private final float speed;

  private final float x;
  private final float thickness;
  private final float increments;
  private final float y;
  private final PImage fireball;

  public ShootLine(PVector position, float size, float speed, GameWindow window) {
    this.position = position.copy();
    this.speed = speed;
    this.x = position.x + size/2;
    this.y = GameWindow.getY();
    this.position.y = GameWindow.getY();
    this.thickness = 10;
    fireball = window.loadImage("../assets/fireball.png");
    this.increments = GameWindow.getY() / speed;
  }

  /**
   * Displays shootLine onto the window
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.strokeWeight(1);
    window.image(fireball, this.x - thickness * 2, position.y - thickness, 42, 64);
    PVector currYellow = new PVector(x + thickness /2, y);
    PVector currRed = new PVector(x - thickness /2, y);
    PVector prevYellow = new PVector(x - thickness /2, y);
    PVector prevRed = new PVector(x + thickness /2, y);
    float maxIncrements = (increments - this.getPosition().y / this.speed);
    for(float i = 0; i < maxIncrements - speed; i++) {
      currYellow.y = (prevYellow.y - this.speed);
      currRed.y = (prevRed.y - this.speed);
      window.stroke(255, 204, 0);
      window.line(currYellow.x, currYellow.y, prevYellow.x, prevYellow.y);
      window.stroke(255, 0, 0);
      window.line(currRed.x, currRed.y, prevRed.x, prevRed.y);
      prevYellow.y = currYellow.y;
      prevRed.y = currRed.y;
      float tempX = prevYellow.x;
      float tempX2 = prevRed.x;
      prevYellow.x = currYellow.x;
      prevRed.x = currRed.x;
      currYellow.x = tempX;
      currRed.x = tempX2;

    }
    window.stroke(0);

  }

  public PVector getPosition() {
    return position;
  }

  /**
   * Updates the shootLine.
   *
   */
  public void update() {
    if(this.position.y > 100 + thickness / 2) {
      this.position.y = (this.position.y - speed);
    }
  }

  public boolean checkHitCeiling() {
    return (this.position.y <= 100 + thickness / 2);
  }
}


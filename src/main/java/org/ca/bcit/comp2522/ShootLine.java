package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;
import java.awt.*;

/**
 * ShootLine. The class that creates the shootLine that the player can use to catch the bubbles.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class ShootLine extends Sprite {

  private PVector velocity;
  protected final float x;
  protected float thickness;
  protected float increments;
  protected final float y;
  private PImage fireball;

  /**
   * Constructor for ShootLine shot by the player to pop bubbles
   * @param position as a PVector
   * @param direction as a PVector
   * @param size as a float
   * @param speed as a float
   * @param color as a Color
   * @param window as a GameWindow
   */
  public ShootLine(PVector position, PVector direction, float size, float speed, Color color, GameWindow window) {
    super(position, direction, size, speed, color, window);
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
    float maxIncrements = increments - this.getPosition().y / this.speed;
    for(float i = 0; i < maxIncrements - speed; i++) {
      currYellow.y = prevYellow.y - this.speed;
      currRed.y = prevRed.y - this.speed;
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

  /**
   * Updates the shootLine
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {

    // hard coded for now, should not go above the scorebar
    if(this.position.y > 100 + thickness / 2) {
      this.position.y = this.position.y - speed;
    } else {Scene.shootLine = null;}
  }
}


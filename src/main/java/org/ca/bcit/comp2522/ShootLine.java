package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Represents the fireball line that the player shoots.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class ShootLine extends Sprite {

  protected final float lineX;
  protected float thickness;
  protected float increments;
  protected final float lineY;
  private final PImage fireball;
  protected final int playerNum;



  /**
   * Constructor for the Line shooting action.
   *
   * @param position PVector
   * @param size float
   * @param speed float
   * @param window GameWindow
   */
  public ShootLine(PVector position, float size, float speed, GameWindow window, int playerNum) {
    super();
    this.position = position.copy();
    this.speed = speed + 1;
    this.lineX = position.x + size / 2;
    this.lineY = GameWindow.getY();
    this.position.y = GameWindow.getY();
    this.playerNum = playerNum;
    this.thickness = 10;
    if (playerNum == 1) {
      fireball = window.loadImage("../assets/fireball.png");
    } else {
      fireball = window.loadImage("../assets/fireball2.png");
    }

    this.increments = GameWindow.getY() / this.speed;
  }

  /**
   * Displays shootLine onto the window.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.strokeWeight(1);
    window.image(fireball, this.lineX - thickness * 2, position.y - thickness, 42, 64);
    PVector currYellow = new PVector(lineX + thickness / 2, lineY);
    PVector currRed = new PVector(lineX - thickness / 2, lineY);
    PVector prevYellow = new PVector(lineX - thickness / 2, lineY);
    PVector prevRed = new PVector(lineX + thickness / 2, lineY);
    float maxIncrements = (increments - this.getPosition().y / this.speed);
    for (float i = 0; i < maxIncrements - speed; i++) {
      currYellow.y = (prevYellow.y - this.speed);
      currRed.y = (prevRed.y - this.speed);
      if (playerNum == 1) {
        window.stroke(255, 204, 0);
      } else {
        window.stroke(255, 0, 255);
      }
      window.line(currYellow.x, currYellow.y, prevYellow.x, prevYellow.y);
      if (playerNum == 1) {
        window.stroke(255, 0, 0);
      } else {
        window.stroke(0, 0, 255);
      }
      window.line(currRed.x, currRed.y, prevRed.x, prevRed.y);
      prevYellow.y = currYellow.y;
      prevRed.y = currRed.y;
      float tempX = prevYellow.x;
      final float tempX2 = prevRed.x;
      prevYellow.x = currYellow.x;
      prevRed.x = currRed.x;
      currYellow.x = tempX;
      currRed.x = tempX2;

    }
    window.stroke(0);

  }

  /**
   * Updates the shootLine.
   *
   */
  public void update() {
    if (this.position.y > thickness / 2) {
      this.position.y = (this.position.y - speed);
    }
  }

  /**
   * Checks if the shootLine has hit the ceiling.
   *
   * @return boolean
   */
  public boolean checkHitCeiling() {
    return (this.position.y <= thickness / 2);
  }
}
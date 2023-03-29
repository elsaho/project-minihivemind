package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import java.awt.*;

/**
 * Line. The class that creates the line that the player can use to catch the bubbles.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class Line extends Sprite {
  /** TODO:
   * 1. Make a basic line
   * 2. update the line so it grows from the floor to the top
   * 3. update the line so it deletes itself when
   * 4. Make the line appear with up or space
   * 5. make the line appear at the feet of the current player
   * 6. Make the line a singleton, cannot be created until it dissapears.
   * 7 Figure out colliding with the bubbles
   * 8. just for the push
   */
  private PVector velocity;
  protected final float x;
  protected float thickness;

  protected float increments;

  protected final float y;
  private PImage fireball;

  public Line(PVector position, PVector direction, float size, float speed, Color color, GameWindow window) {
    super(position, direction, size, speed, color, window);
    this.x = position.x + size/2;
    this.y = GameWindow.getY();
    this.position.y = GameWindow.getY();
    this.thickness = 10;
    fireball = window.loadImage("../assets/fireball.png");
    this.increments = GameWindow.getY() / speed;
  }

  public void display(PApplet parent) {
    window.strokeWeight(1);
    parent.image(fireball, this.x - thickness * 2, position.y - thickness, 42, 64);
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


  public void update(PApplet parent) {

    // hard coded for now, should not go above the scorebar
    if(this.position.y > 100 + thickness / 2) {
      this.position.y = this.position.y - speed;
    } else {Scene.line = null;}
  }

  @Override
  public boolean collided() {
    return false;
  }
}


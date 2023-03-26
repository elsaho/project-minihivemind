package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
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

public class Line extends Sprite implements Drawable, Moveable {

  private PVector velocity;
  private final float x;
  private float thickness;

  private final float y;
  private PImage fireball;

  public Line(PVector position, PVector direction, float size, float speed, Color color, GameWindow window) {
    super(position, direction, size, speed, color, window);
    this.x = position.x - (size);
    this.y = GameWindow.getY();
    this.position.y = GameWindow.getY();
    this.thickness = 21;
    fireball = window.loadImage("../assets/fireball.png");
  }

  public void display(PApplet parent) {
    window.strokeWeight(thickness);
    window.stroke(204, 102, 0);// Beastly
    window.line(x, y, x, this.position.y); //line(x1, y1, x2, y2)
    window.strokeWeight(1);
    window.stroke(0);
//    parent.image(fireball, this.position.x + (size/3), this.position.y, 100, 100);
  }





  public void move() {
    // TODO Auto-generated method stub
    // Move line
  }

  @Override
  public void draw() {

  }

  public void update(PApplet parent) {

    if(this.position.y > 0) {
      this.position.y = this.position.y - speed;
    } else {Scene.line = null;}
  }

  @Override
  public boolean collided() {
    return false;
  }
}


package org.ca.bcit.comp2522;

import processing.core.PApplet;
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
 */

public class Line extends Sprite implements Drawable, Moveable {

  private PVector velocity;
  private final float x;

  public Line(PVector position, PVector direction, float size, float speed, Color color, GameWindow window, PVector velocity) {
    super(position, direction, size, speed, color, window);
    this.velocity = velocity;
    this.x = position.x;
  }

  public void display(PApplet parent) {
    window.strokeWeight(30);
    window.stroke(204, 102, 0);// Beastly
    window.line(x, 600, x, 300); //line(x1, y1, x2, y2)
  }





  public void move() {
    // TODO Auto-generated method stub
    // Move line
  }

  @Override
  public void draw() {

  }

  public void update(PApplet parent, Player player) {
    this.position.x = player.getPosition().x;
  }
}


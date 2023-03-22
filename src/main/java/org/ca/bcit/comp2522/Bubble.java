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


  // DO bounce overrirde
//  public void bounce() {
//    if (position.y == 0) {
//      //something with velocity
//    }
//  }
  @Override
  public void update() {

//    if (parent.keyPressed) {
//      if (parent.keyCode == 37) { // left arrow key
//        if (position.x - speed < 0) {
//          position.x = 0;
//        }
//        position.x -= speed;
//      } else if (parent.keyCode == 39) { // right arrow key
//        if (position.x + speed > GameWindow.getX() - 10) {
//          position.x = GameWindow.getX() - 10;
//        }
//        position.x += speed;
//      }
//    }
  }

  @Override
  public void pop() {

  }
}

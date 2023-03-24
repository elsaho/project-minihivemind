package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


import java.awt.*;

public class Player extends Sprite{

  private PImage playerImage;
  PApplet parent;
  public Player(PVector position, PVector direction, float size, float speed, Color color, GameWindow window) {
    super(position, direction, size, speed, color, window);
    playerImage = window.loadImage("player.png");
  }

  public void update(PApplet parent) {
    if (parent.keyPressed) {
    if (parent.keyCode == 37) { // left arrow key
      if (position.x - speed < 0) {
        position.x = 0;
      }
        position.x -= speed;
      } else if (parent.keyCode == 39) { // right arrow key
      if (position.x + speed > GameWindow.getX() - 10) {
        position.x = GameWindow.getX() - 10;
      }
        position.x += speed;
      }
    }
  }
  @Override
  public void display(PApplet parent) {
    parent.pushStyle();
    parent.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    parent.image(playerImage, this.position.x, this.position.y, size, size);
    parent.popStyle();
  }
}



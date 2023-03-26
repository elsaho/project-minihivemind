package org.ca.bcit.comp2522;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Sprite. The base class for all sprites in the game.
 * A lot of classes extend this class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public abstract class Sprite implements Collidable {
  protected PVector position;
  protected PVector direction;
  protected float size;
  protected float speed;
  protected Color color;
  protected GameWindow window;


  /**
   * Constructor for objects of class Sprite.
   *
   * @param position as a PVector
   * @param direction as a PVector
   * @param size as a float
   * @param speed as a float
   * @param color as a Color
   * @param window as a GameWindow
   */
  public Sprite(PVector position, PVector direction,
                float size, float speed, Color color, GameWindow window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public PVector getPosition() {
    return position.copy();
  }


  public void update() {
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public float getSize() {
    return size;
  }

  /**
   * Checks if a bubble collides with a player.
   *
   * @param bubble as a Bubble
   * @param player as a Player
   * @return true if the bubble collides with the player, false otherwise
   */
  public static boolean collided(Bubble bubble, Player player) {
    float bubbleX = bubble.getPosition().x;
    float bubbleY = bubble.getPosition().y;
    float playerX = player.getPosition().x;
    float playerY = player.getPosition().y;
    float playerWidth = player.getSize();
    float playerHeight = player.getSize();

    // find the closest point on the player to the bubble
    float closestX = clamp(bubbleX, playerX, playerX + playerWidth);
    float closestY = clamp(bubbleY, playerY, playerY + playerHeight);

    // calculate the distance between the closest point and the bubble center
    float distX = bubbleX - closestX;
    float distY = bubbleY - closestY;
    float distance = (float) Math.sqrt((distX * distX) + (distY * distY));

    // check if the bubble collides with the player
    if (distance <= bubble.getSize() / 2) {
      return true;
    }

    return false;
  }

  protected static float clamp(float value, float min, float max) {
    return Math.max(min, Math.min(max, value));
  }



  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  /**
   * Displays the sprite.
   *
   * @param parent as a PApplet
   */
  public void display(PApplet parent) {
    parent.pushStyle();
    parent.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    parent.ellipse(this.position.x, this.position.y, size, size);
    parent.popStyle();
  }

  public void setup(PApplet parent) {
  }
}


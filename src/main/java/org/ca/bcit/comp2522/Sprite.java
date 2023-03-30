package org.ca.bcit.comp2522;

import java.awt.*;

import processing.core.PVector;

/**
 * Sprite. The base class for all sprites in the game.
 * A lot of classes extend this class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public abstract class Sprite {

  //Sprite properties
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

  /**
   * Gets direction of sprite
   * @return PVector
   */
  public PVector getDirection() {
    return direction.copy();
  }

  /**
   * Gets position of sprite
   * @return PVector
   */
  public PVector getPosition() {
    return position.copy();
  }

  /**
   * Gets size of sprite
   * @return size as a float
   */
  public float getSize() {
    return size;
  }

//  /**
//   * Checks if the shootLine shot by player has collided with bubble
//   * @param shootLine as a ShootLine
//   * @param bubble as a Bubble
//   * @return boolean
//   */
//  public static boolean collided(ShootLine shootLine, Bubble bubble) {
//    PVector lineTemp = new PVector(shootLine.x, shootLine.getPosition().y);
//    float bubbleRadius = bubble.getSize() / 2;
//    PVector bubbleTemp = bubble.getPosition().copy().add(new PVector(bubbleRadius, bubbleRadius));
//    if (lineTemp.y < bubbleTemp.y) {
//      lineTemp.y = bubbleTemp.y;
//    }
//    float diff = lineTemp.dist(bubbleTemp);
//    return diff < bubbleRadius;
//  }

  /**
   * Updates position of sprite
   */
  public void update() {
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

//  /**
//   * Checks if a bubble collides with a player.
//   *
//   * @param bubble as a Bubble
//   * @param player as a Player
//   * @return true if the bubble collides with the player, false otherwise
//   */
//  public static boolean collided(Bubble bubble, Player player) {
//    float bubbleX = bubble.getPosition().x;
//    float bubbleY = bubble.getPosition().y;
//    float playerX = player.getPosition().x;
//    float playerY = player.getPosition().y;
//    float playerWidth = player.getSize();
//    float playerHeight = player.getSize();
//
//    // find the closest point on the player to the bubble
//    float closestX = clamp(bubbleX, playerX, playerX + playerWidth);
//    float closestY = clamp(bubbleY, playerY, playerY + playerHeight);
//
//    // calculate the distance between the closest point and the bubble center
//    float distX = bubbleX - closestX;
//    float distY = bubbleY - closestY;
//    float distance = (float) Math.sqrt((distX * distX) + (distY * distY));
//    return (distance <= bubble.getSize() / 2);
//  }

  /**
   * Helper method for the collided function to find the closest point of player to bubble
   * @param value as a float
   * @param min as a float
   * @param max as a float
   * @return float
   */
  protected static float clamp(float value, float min, float max) {
    return Math.max(min, Math.min(max, value));
  }

  /**
   * Displays the sprite.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  /**
   * Sets up the sprite, fully implemented in the child classes
   * @param window as a GameWindow
   */
  public void setup(GameWindow window) {
  }
}


package org.ca.bcit.comp2522;

import processing.core.PVector;

import java.awt.*;

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

  public Sprite() {

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

  /**
   * Updates position of sprite
   *
   * @return
   */

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


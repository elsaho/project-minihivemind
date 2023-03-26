package org.ca.bcit.comp2522;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Bubble class creates the bubbles that the player can pop.
 * It extends Sprite and implements Poppable.
 *
 * @author Elsa Ho, Mai Vu
 * @version 2023
 */
public class Bubble extends Sprite implements Poppable {
  // Constants
  private static final float GRAVITY = 0.1f;

  // Properties
  public PVector velocity;
  private Line line;
  private PImage bubbleImage;
  private float startY;
  private float currentX = size;
  private float bounceVelocity;

  /**
   * Constructor for Bubble class.
   *
   * @param position - position of the bubble
   * @param direction - direction of the bubble
   * @param size - size of the bubble
   * @param speed - speed of the bubble
   * @param color - color of the bubble
   * @param window - GameWindow instance
   * @param velocity - velocity of the bubble
   */
  public Bubble(PVector position, PVector direction, float size,
                float speed, Color color, GameWindow window, PVector velocity) {
    super(position, direction, size, speed, color, window);
    this.velocity = velocity;
    this.bubbleImage = window.loadImage("../assets/bubble.png");
    this.startY = position.y;
  }

  /**
   * Bounce method allows bubbles to bounce off floors and walls.
   */
  public void bounce() {
    // Calculate bounce velocity
    this.bounceVelocity = -6f;
    //this.bounceVelocity = -sqrt(4 * GRAVITY * (GameWindow.getY() - this.startY + size));

    // Check for floor collision
    if (this.position.y + size / 2 >= GameWindow.getY()) {
      this.position.y = GameWindow.getY() - size; // Move bubble to just above the floor
      this.velocity.y = bounceVelocity; // Reverse velocity
    } else {
      this.position.y += this.velocity.y; // Move bubble along y-axis
    }

    // Check for wall collision
    if (this.position.x + this.velocity.x > GameWindow.getX() - size) {
      this.position.x = GameWindow.getX() - size;
      this.velocity.x = -this.velocity.x;
      this.currentX = this.position.x - size;
    } else if (this.position.x + this.velocity.x < 0) {
      this.position.x = 0;
      this.velocity.x = -this.velocity.x;
    } else {
      // Add a small amount to the x position
      if (currentX == size) {
        position.x += 3;
        if (position.x == GameWindow.getX() - size) {
          currentX = position.x;
        }
      } else {
        if (position.x <= 0) {
          currentX = size;
        }
        position.x -= 3;
      }
    }

    velocity.y += GRAVITY;
    position.add(velocity);
  }

  /** This method removes the top element from the stack.
   */
  @Override
  public void pop() {
  }

  /** This method returns a boolean value indicating if a collision has occurred.
   *
   * @return true if a collision has occurred, false otherwise.
   */
  @Override
  public boolean collided() {
    return false;
  }

  /** This method displays the bubble on the screen
   *  using the PApplet instance provided as a parameter.
   *
   *  @param parent the PApplet instance used to display the bubble.
   */
  @Override
  public void display(PApplet parent) {
    parent.pushStyle();
    parent.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    parent.image(bubbleImage, this.position.x, this.position.y, size, size);
    parent.popStyle();
  }
}

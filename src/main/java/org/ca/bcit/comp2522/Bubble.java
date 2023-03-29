package org.ca.bcit.comp2522;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Bubble class creates the bubbles that the player can pop.
 * It extends Sprite.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Bubble extends Sprite {
  /**
   * Gravity constant
   */
  private static final float GRAVITY = 0.1f;

  /**
   * Minimum size of a bubble
   */
  public static final float MIN_SIZE = 25f;

  /**
   * Bubble velocity
   */
  public PVector velocity;
  /**
   * image of bubble
   */
  private final PImage bubbleImage;

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
  }

  /**
   * Bounce method allows bubbles to bounce off floors and walls.
   */
  public void bounce() {
    // set bounce velocity, change to static final if we don't want to have diff bounce velocity
    float bounceVelocity = -6f;

    // Check for floor collision
    if (this.position.y + size / 2 >= GameWindow.getY()) {
      this.position.y = GameWindow.getY() - size; // Move bubble to just above the floor
      this.velocity.y = bounceVelocity; // Reverse velocity
    } else {
      this.position.y += this.velocity.y; // Move bubble along y-axis
    }

    // Check for wall collision
    if (this.position.x >= GameWindow.getX() - size || this.position.x <= 0) {
      this.velocity.x = -this.velocity.x;
    }
    velocity.y += GRAVITY;
    position.add(velocity);
  }

  /** This method returns an arraylist of bubbles containing 2 smaller bubbles.
   *
   * @return newBubbles - arraylist of bubbles
   */
  public ArrayList<Bubble> spawnBubbles() {
    ArrayList<Bubble> newBubbles = new ArrayList<>();
    float newSize = size - MIN_SIZE;
    PVector newVelocity1 = new PVector(-velocity.x, -Math.abs(velocity.y));
    PVector newVelocity2 = new PVector(velocity.x, -Math.abs(velocity.y));
    Bubble bubble1 = new Bubble(new PVector(position.x, position.y), new PVector(-1, -1),
        newSize, speed, color, window, newVelocity1);
    Bubble bubble2 = new Bubble(new PVector(position.x, position.y), new PVector(1, -1),
        newSize, speed, color, window, newVelocity2);
    newBubbles.add(bubble1);
    newBubbles.add(bubble2);
    return newBubbles;
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
   *  using the GameWindow instance provided as a parameter.
   *
   *  @param window the GameWindow instance used to display the bubble.
   */
  @Override
  public void display(GameWindow window) {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.image(bubbleImage, this.position.x, this.position.y, size, size);
    window.popStyle();
  }

}

package org.ca.bcit.comp2522;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
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
  // Constants
  private static final float GRAVITY = 0.1f;

  // Properties
  public PVector velocity;
  private Line line;
  private PImage bubbleImage;
  private boolean flag = true;
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
  }

  /**
   * Bounce method allows bubbles to bounce off floors and walls.
   */
  public void bounce() {
    // Calculate bounce velocity
    this.bounceVelocity = -6f;

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

  public ArrayList<Bubble> spawnBubbles() {
    ArrayList<Bubble> newBubbles = new ArrayList<>();
    float newSize = size / 2;
    PVector newVelocity1 = new PVector(-velocity.x, -velocity.y);
    PVector newVelocity2 = new PVector(velocity.x, -velocity.y);
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

package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Bubble class creates the bubbles that the player can pop.
 * It extends Sprite.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Bubble extends Sprite implements Collidable {
  //Gravity constant
  private static final float GRAVITY = 0.1f;

  //Minimum size of a bubble
  public static final float MIN_SIZE = 25f;

  //Bubble image
  private PImage bubbleImage;

  //Velocity of the bubble
  public PVector velocity;


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
  public Bubble(PVector position, PVector direction, PVector size,
                float speed, Color color, GameWindow window, PVector velocity) {
    super(position, direction, size, speed, color, window);
    this.velocity = velocity;
    if (this.color.getRed() == 255) {
      this.bubbleImage = window.loadImage("../assets/pinkBubble.png");
    } else if (this.color.getRed() == 0) {
      this.bubbleImage = window.loadImage("../assets/bubble.png");
    }
  }

  /** Bounce method allows bubbles to bounce off floors and walls.
   */
  public void update() {
    // set bounce velocity, change to static final if we don't want to have diff bounce velocity
    float bounceVelocity = -2.7f - (this.size.y / MIN_SIZE);

    // Check for floor collision
    if (this.position.y + size.y / 2 >= GameWindow.getY()) {
      this.position.y = GameWindow.getY() - size.y; // Move bubble to just above the floor
      this.velocity.y = bounceVelocity; // Reverse velocity
    } else {
      this.position.y += this.velocity.y; // Move bubble along y-axis
    }

    // Check for wall collision
    if (this.position.x >= GameWindow.getX() - size.x || this.position.x <= 0) {
      this.velocity.x = -this.velocity.x;
    }
    velocity.y += GRAVITY;
    position.add(velocity);
  }

  /** Returns an arraylist of bubbles containing 2 smaller bubbles.
   *
   * @return newBubbles - arraylist of bubbles
   */
  public ArrayList<Bubble> spawnBubbles() {
    ArrayList<Bubble> newBubbles = new ArrayList<>();
    PVector newSize = new PVector(size.x - MIN_SIZE, size.y - MIN_SIZE);
    PVector newVelocity1 = new PVector(-velocity.x, velocity.y < 0 ? velocity.y : -velocity.y);
    PVector newVelocity2 = new PVector(velocity.x, velocity.y < 0 ? velocity.y : -velocity.y);
    Bubble bubble1 = new Bubble(new PVector(position.x, position.y), new PVector(-1, -1),
        newSize, speed, color, window, newVelocity1);
    Bubble bubble2 = new Bubble(new PVector(position.x, position.y), new PVector(1, -1),
        newSize, speed, color, window, newVelocity2);
    newBubbles.add(bubble1);
    newBubbles.add(bubble2);
    return newBubbles;
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
    window.image(bubbleImage, this.position.x, this.position.y, size.x, size.y);
    window.popStyle();
  }

  /**
   * Handles collisions between bubbles and other objects.
   *
   * @param o as an Object
   * @return true if collided
   */
  @Override
  public boolean collided(Object o) {
    if (o instanceof Player player) {
      // Handle collision with player
      float playerX = player.getPosition().x;
      float playerY = player.getPosition().y;
      float playerWidth = player.getSize().x;
      float playerHeight = player.getSize().y;

      // find the closest point on the player to the bubble
      float closestX = clamp(this.getPosition().x, playerX, playerX + playerWidth);
      float closestY = clamp(this.getPosition().y, playerY, playerY + playerHeight);

      // calculate the distance between the closest point and the bubble center
      float distX = this.getPosition().x - closestX;
      float distY = this.getPosition().y - closestY;
      float distance = (float) Math.sqrt((distX * distX) + (distY * distY));

      return (distance <= this.getSize().y / 2);

    } else if (o instanceof ShootLine shootLine) {
      PVector lineTemp = new PVector(shootLine.lineX, shootLine.getPosition().y);
      float bubbleRadius = this.getSize().y / 2;
      PVector bubbleTemp = this.getPosition().copy().add(new PVector(bubbleRadius, bubbleRadius));
      if (lineTemp.y < bubbleTemp.y) {
        lineTemp.y = bubbleTemp.y;
      }
      float diff = lineTemp.dist(bubbleTemp);
      return diff < bubbleRadius;
    }
    return false;
  }

  /** This method update the score and lives of the player.
   *
   * @param bubble - Bubble instance
   */
  public void scoreUpdate(Bubble bubble) {
    if (Lives.getInstance().getLives() > 0) {
      Scene.sounds.playOof();
      ScoreBar.getInstance().update(bubble, false, true);
      Scene.isImmune = true;
      Scene.lastCollisionTime = System.currentTimeMillis();
    } else {
      Scene.sounds.playLoseAudio();
    }
  }

  public int getColorR() {
    return this.color.getRed();
  }
}

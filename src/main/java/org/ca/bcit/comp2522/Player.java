package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

/** Player class. The sprite that the user controls.
 * Can move left and right and shoot bullets. Extends Sprite class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Player extends Sprite {
  private final PImage playerLeft;
  private final PImage playerRight;
  private final PImage shootLeft;
  private final PImage shootRight;
  private boolean playerFaceLeft;

  /** Constructor for Player class.
   *
   *@param position the position of the player as a PVector.
   *@param direction the direction of the player as a PVector.
   *@param size the size of the player as a float.
   *@param speed the speed of the player as a float.
   *@param color the color of the player as a Color.
   *@param window the GameWindow instance.
   */
  public Player(final PVector position, final PVector direction, final float size,
                final float speed, final Color color, final GameWindow window) {
    super(position, direction, size, speed, color, window);
    playerLeft = window.loadImage("../assets/CharLeft.png");
    playerRight = window.loadImage("../assets/CharRight.png");
    shootLeft = window.loadImage("../assets/ShootLeft.png");
    shootRight = window.loadImage("../assets/ShootRight.png");
    playerFaceLeft = true;
  }

  /** Updates the player's position based on the arrow key pressed by the user.
   *
   * @param window the GameWindow instance used to update the player's position.
   */
  public void update(final GameWindow window) {
    setPlayerDirection(window);
    boolean moveLeft = window.inputHandler.isLeft();

    // Code to prevent player from moving outside of window bounds
    if (moveLeft) {
      if (position.x < -size/2) {
        position.x = -size/2;
      }
      position.x -= speed;
    } else if (window.inputHandler.isRight()) {
      if (position.x > GameWindow.getX() - size - size/2) {
        position.x = GameWindow.getX() - size - size/2;
      }
      position.x += speed;
    }

    if (window.inputHandler.isUp()) {
      // Shoot a projectile or perform some other action
      //Currently unused, ideally used for refactor in future
    }
  }

  /** Displays the player on the screen.
   *
   * @param window the GameWindow instance used to display the player.
   */
  @Override
  public void display(final GameWindow window) {
    window.pushStyle();
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    boolean isUp = window.inputHandler.isUp();


    if (isUp) {
      window.image(playerFaceLeft ? shootLeft : shootRight, position.x + 42, position.y, 42, 64);
    } else {
      window.image(playerFaceLeft ? playerLeft : playerRight, position.x + 42, position.y, 42, 64);
    }

    window.popStyle();
  }

  /** Sets the player's facing direction
   *
   * @param window GameWindow instance used to get the user input.
   */
  private void setPlayerDirection(final GameWindow window) {
    if (window.inputHandler.isLeft()) {
      playerFaceLeft = true;
    } else if (window.inputHandler.isRight()) {
      playerFaceLeft = false;
    } else {
      //do nothing (this is on purpose!)
    }
  }

}



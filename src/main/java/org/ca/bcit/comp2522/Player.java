package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

import static processing.core.PConstants.UP;

/** Player class. The sprite that the user controls.
 * Can move left and right and shoot bullets. Extends Sprite class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Player extends Sprite {
  private final PImage playerLeft;
  private final PImage playerRight;
  private PImage shootLeft;
  private PImage shootRight;
  /**
   * If player's last input is facing the left arrow key
   */
  private boolean isLeft;
  private final GameWindow window;

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
    isLeft = true;
    this.window = window;
  }

  /** Updates the player's position based on the arrow key pressed by the user.
   *
   * @param window the GameWindow instance used to update the player's position.
   */
  public void update(final GameWindow window) {

    if (window.inputHandler.isLeft()) {
      if (position.x - speed < 0) {
        position.x = 0;
      }
      position.x -= speed;
    }

    if (window.inputHandler.isRight()) {
      if (position.x + speed > GameWindow.getX() - size) {
        position.x = GameWindow.getX() - size;
      }
      position.x += speed;
    }

      if (window.inputHandler.isUp()) {
        // Shoot a projectile or perform some other action
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
    if (window.keyCode == UP && isLeft) {
      window.image(shootLeft, position.x + 42, position.y, 42, 64);
    } else if (window.keyCode == UP && !isLeft) {
      window.image(shootRight, position.x + 42, position.y, 42, 64);
    } else if (isLeft) {
      window.image(playerLeft, position.x + 42, position.y, 42, 64);
    } else {
      window.image(playerRight, position.x + 42, position.y, 42, 64);
    }
    window.popStyle();
  }

  /** Returns false since the player cannot collide with anything in this game.
   *
   * @return false
   */
  @Override
  public boolean collided() {
    return false;
  }

}



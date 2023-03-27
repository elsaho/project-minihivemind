package org.ca.bcit.comp2522;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
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
  private boolean isLeft;
  private final PApplet parent;

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
    parent = window;
  }

  /** Updates the player's position based on the arrow key pressed by the user.
   *
   * @param parent the PApplet instance used to update the player's position.
   */
  public void update(final PApplet parent) {
    if (parent.keyPressed) {
      if (parent.keyCode == PConstants.LEFT) {
        // left arrow key
        isLeft = true;
        if (position.x - speed < 0) {
          position.x = 0;
        }
        position.x -= speed;
      } else if (parent.keyCode == PConstants.RIGHT) {
        // right arrow key
        isLeft = false;
        if (position.x + speed > GameWindow.getX() - size) {
          position.x = GameWindow.getX() - size;
        }
        position.x += speed;
      }
    }
  }

  /** Displays the player on the screen.
   *
   * @param parent the PApplet instance used to display the player.
   */
  @Override
  public void display(final PApplet parent) {
    parent.pushStyle();
    parent.fill(color.getRed(), color.getGreen(), color.getBlue());
    if ((window.keyCode == UP) && isLeft) {
      parent.image(shootLeft, position.x, position.y, 42, 64);
    } else if ((window.keyCode == UP) && !isLeft) {
      parent.image(shootRight, position.x, position.y, 42, 64);
    } else if (isLeft) {
      parent.image(playerLeft, position.x, position.y, 42, 64);
    } else {
      parent.image(playerRight, position.x, position.y, 42, 64);
    }
    parent.popStyle();
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



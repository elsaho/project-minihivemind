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

  ShootLine playersLine;
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
    playersLine = null;
  }

  /** Updates the player's position based on the arrow key pressed by the user.
   *
   * @param window the GameWindow instance used to update the player's position.
   */
  public void update(final GameWindow window) {


    if (window.inputHandler.isLeft()) {
      if (position.x < - size/2) {
        position.x = -size/2;

      }
      this.isLeft = true;
      position.x -= speed;
    }

    if (window.inputHandler.isRight()) {
      if (position.x > GameWindow.getX() - size - size/2) {
        position.x = GameWindow.getX() - size - size/2;
      }
      this.isLeft = false;
      position.x += speed;
    }

      if (window.inputHandler.isUp()) {
        System.out.println("input up right before shoot");
        makeLine(window);
        }
      }

  void makeLine(GameWindow window) {
    System.out.println("right before null");
    if (playersLine == null) {
      System.out.println("after *******");
          playersLine = new ShootLine(
              new PVector(this.position.x + this.size / 2, this.position.y),
              this.size, this.speed, window
          );
//          sounds.playShoot();
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
    if (window.inputHandler.isUp() && window.inputHandler.isLeft()) {
      window.image(shootLeft, position.x + 42, position.y, 42, 64);
    } else if (window.inputHandler.isUp() && window.inputHandler.isRight()) {
      window.image(shootRight, position.x + 42, position.y, 42, 64);
    } else if (window.inputHandler.isLeft() || this.isLeft) {
      window.image(playerLeft, position.x + 42, position.y, 42, 64);
    } else if (window.inputHandler.isRight() || !this.isLeft){
      window.image(playerRight, position.x + 42, position.y, 42, 64);
    }
    if (playersLine != null) {
      playersLine.update(window);
      playersLine.display(window);
      if (playersLine.checkHitCeiling()) {
        playersLine = null;
      }
    }

    window.popStyle();
  }

  public ShootLine getPlayersLine() {
    return playersLine;
  }

  public void setPlayersLine(ShootLine playersLine) {
    this.playersLine = playersLine;
  }
}



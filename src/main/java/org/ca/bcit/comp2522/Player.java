package org.ca.bcit.comp2522;

import processing.core.PImage;
import processing.core.PVector;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.FileNotFoundException;

/** Player class. The sprite that the user controls.
 * Can move left and right and shoot bullets. Extends Sprite class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Player extends Sprite {
  private final int left;
  private final int right;
  private final int up;
  private final int playerNo;
  private final InputHandler handler;
  ShootLine playersLine;
  private PImage playerLeft;
  private PImage playerRight;
  private PImage shootLeft;
  private PImage shootRight;
  private boolean playerFaceLeft;
  private final SoundEffects sounds = new SoundEffects();

  public int getLeft() {
    return left;
  }

  public int getRight() {
    return right;
  }

  public int getUp() {
    return up;
  }

  public int getPlayerNo() {
    return playerNo;
  }

  /** Constructor for Player class.
   *
   *@param position the position of the player as a PVector.
   *@param direction the direction of the player as a PVector.
   *@param size the size of the player as a float.
   *@param speed the speed of the player as a float.
   *@param color the color of the player as a Color.
   *@param window the GameWindow instance.
   */

  public Player(final PVector position, final PVector direction, final PVector size,
                final float speed, final Color color, final GameWindow window, int left, int right, int up, int playerNo)
          throws LineUnavailableException, FileNotFoundException {
    super(position, direction, size, speed, color, window);
    handler = new InputHandler(left, right, up);
    window.addInputHandler(handler);
    this.left = left;
    this.right = right;
    this.up = up;
    this.playerNo = playerNo;

    if (playerNo == 1) {
      playerLeft = window.loadImage("../assets/CharLeft.png");
      playerRight = window.loadImage("../assets/CharRight.png");
      shootLeft = window.loadImage("../assets/ShootLeft.png");
      shootRight = window.loadImage("../assets/ShootRight.png");
    } else {
      playerLeft = window.loadImage("../assets/P2Left.png");
      playerRight = window.loadImage("../assets/P2Right.png");
      shootLeft = window.loadImage("../assets/P2ShootLeft.png");
      shootRight = window.loadImage("../assets/P2ShootRight.png");
    }

    playerFaceLeft = true;
    this.window = window;
    playersLine = null;
  }

  /** Updates the player's position based on the arrow key pressed by the user.
   */
  public void update() {
    setPlayerDirection();
    boolean moveLeft = handler.isLeft();

    // Code to prevent player from moving outside of window bounds
    if (moveLeft) {
      if (position.x < -size.x) {
        position.x = -size.x;
      }
      position.x -= speed;
    } else if (handler.isRight()) {

      if (position.x > GameWindow.getX() - size.x * 2) {
        position.x = GameWindow.getX() - size.x * 2;
      }
      position.x += speed;
    }

      if (handler.isUp()) {
        makeLine(window);
        }
      }

  void makeLine(GameWindow window) {
    if (playersLine == null) {
          playersLine = new ShootLine(
              new PVector(this.position.x + this.size.x, this.position.y),
              this.size.x, this.speed, window
          );
          sounds.playShoot();
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
    boolean isUp = handler.isUp();


    if (isUp) {
      window.image(playerFaceLeft ? shootLeft : shootRight, position.x + size.x, position.y, size.x, size.y);
    } else {
      window.image(playerFaceLeft ? playerLeft : playerRight, position.x + size.x, position.y, size.x, size.y);
    }
    if (playersLine != null) {
      playersLine.update();
      playersLine.display(window);
      if (playersLine.checkHitCeiling()) {
        playersLine = null;
      }
    }

    window.popStyle();
  }

  /** Sets the player's facing direction.
   */
  private void setPlayerDirection() {
    if (handler.isLeft()) {
      playerFaceLeft = true;
    } else if (handler.isRight()) {
      playerFaceLeft = false;
    } else {
      //do nothing (this is on purpose!)
    }
  }


  public ShootLine getPlayersLine() {
    return playersLine;
  }

  public void setPlayersLine(ShootLine playersLine) {
    this.playersLine = playersLine;
  }

  public void setPlayerLeft(PImage i) {
    playerLeft = i;
  }

  public void setPlayerRight(PImage i) {
    playerRight = i;
  }

  public void setShootLeft(PImage i) {
    shootLeft = i;
  }

  public void setShootRight(PImage i) {
    shootRight = i;
  }
}



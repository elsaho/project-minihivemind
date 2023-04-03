package org.ca.bcit.comp2522;

import java.awt.Color;
import java.io.FileNotFoundException;
import javax.sound.sampled.LineUnavailableException;
import processing.core.PImage;
import processing.core.PVector;

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
  private final SoundEffects sounds = new SoundEffects();

  private final PImage playerLeft;
  private final PImage playerRight;
  private final PImage shootLeft;
  private final PImage shootRight;

  private boolean playerFaceLeft;

  private final GameWindow window;
  private ShootLine playersLine;

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
                final float speed, final Color color, final GameWindow window,
                int left, int right, int up, int playerNo)
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

  /** Updates the player's position based on the arrow key pressed by the user.
   */
  public void update() {
    if (playersLine != null) {
      playersLine.update();
      playersLine.display(window);
      if (playersLine.checkHitCeiling()) {
        playersLine = null;
      }
    }
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
      window.image(playerFaceLeft ? shootLeft : shootRight,
          position.x + size.x, position.y, size.x, size.y);
    } else {
      window.image(playerFaceLeft ? playerLeft : playerRight,
          position.x + size.x, position.y, size.x, size.y);
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
    }
  }

  public ShootLine getPlayersLine() {
    return playersLine;
  }

  public void setPlayersLine(ShootLine playersLine) {
    this.playersLine = playersLine;
  }

}



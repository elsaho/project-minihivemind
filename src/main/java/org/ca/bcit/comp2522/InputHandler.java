package org.ca.bcit.comp2522;

import processing.core.PConstants;

public class InputHandler {
  private GameWindow gameWindow;
  private Thread inputThread;
  private boolean leftPressed;
  private boolean rightPressed;

  private boolean upPressed;

  public InputHandler(GameWindow gameWindow) {
    // GameWindow should be a singleton.
    this.gameWindow = gameWindowGetInstance();
    inputThread = new Thread(() -> {
      while (true) {

        if (gameWindow.keyPressed()) {
          if (gameWindow.keyCode == PConstants.LEFT) {
            leftPressed = true;
          } else if (gameWindow.keyCode == PConstants.RIGHT) {
            rightPressed = true;
          } else if (gameWindow.keyCode() == PConstants.UP) {
            upPressed = true;
          }
        } else if (gameWindow.keyReleased()) {
          if (gameWindow.keyCode() == PConstants.LEFT) {
            leftPressed = false;
          } else if (gameWindow.keyCode() == PConstants.RIGHT) {
            rightPressed = false;
          }  else if (gameWindow.keyCode() == PConstants.UP) {
          upPressed = false;
        }
        }
      }
    });
    inputThread.start();
  }

  public boolean isLeftPressed() {
    return leftPressed;
  }

  public boolean isRightPressed() {
    return rightPressed;
  }
}

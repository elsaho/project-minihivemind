package org.ca.bcit.comp2522;

import processing.core.PConstants;

public class InputHandler {

  public static InputHandler inputHandler;

  protected static GameWindow window;
  private boolean isLeft;
  private boolean isRight;
  private boolean isUp;

  public InputHandler(GameWindow window) {
    System.out.println("inputhandler object constructor was invoked");
    this.window = window;
    this.isLeft = false;
    this.isRight = false;
    this.isUp = false;
  }

  public static InputHandler GetInputHandlerInstance() {
    System.out.println("getInputinstance was called");
    if (inputHandler == null) {
      inputHandler = new InputHandler(window);
    }
    return inputHandler;
  }
  public boolean isLeft() {
    return isLeft;
  }

  public boolean isRight() {
    return isRight;
  }

  public boolean isUp() {
    return isUp;
  }

  public void update(boolean newState) {
    switch (window.keyCode) {
      case PConstants.UP:
        isUp = newState;
        break;

      case PConstants.LEFT:
        isLeft = newState;
        break;

      case PConstants.RIGHT:
        isRight = newState;
        break;

      default: break;

    }


  }

}

package org.ca.bcit.comp2522;

public class InputHandler {
  private final int left;

  private final int right;

  private final int up;

  protected static GameWindow window;
  private boolean isLeft;
  private boolean isRight;
  private boolean isUp;
  public InputHandler(GameWindow window, int left, int right, int up) {
    this.window = window;
    this.isLeft = false;
    this.isRight = false;
    this.isUp = false;
    this.left = left;
    this.right = right;
    this.up = up;
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
    if (window.keyCode == up) {
      isUp = newState;
    } else if (window.keyCode == left) {
      isLeft = newState;
    } else if (window.keyCode == right) {
      isRight = newState;
    }
  }
  }


package org.ca.bcit.comp2522;

/** Handles input from the user.
 */
public class PlayerInputHandler {
  private final int left;
  private final int right;
  private final int up;
  private boolean isLeft;
  private boolean isRight;
  private boolean isUp;

  /** Creates a new input handler.
   *
   * @param left the key code for the left key
   * @param right the key code for the right key
   * @param up the key code for the up key
   */
  public PlayerInputHandler(int left, int right, int up) {
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

  /** Updates the state of the input handler.
   *
   * @param window the window to update
   * @param newState the new state of the input handler
   */
  public void update(GameWindow window, boolean newState) {
    if (window.keyCode == up) {
      isUp = newState;
    } else if (window.keyCode == left) {
      isLeft = newState;
    } else if (window.keyCode == right) {
      isRight = newState;
    }
  }
}


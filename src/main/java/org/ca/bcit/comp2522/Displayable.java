package org.ca.bcit.comp2522;

/**
 * Interface for all Displayable objects.
 * All objects that need to be displayed should implement this interface.
 * This interface is used in the GameWindow class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public interface Displayable {
  /**
   * Displays the object.
   *
   * @param window as a GameWindow
   */
  void display(GameWindow window);
}

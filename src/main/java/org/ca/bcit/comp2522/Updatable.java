package org.ca.bcit.comp2522;

/**
 * Interface for all Updatable objects.
 * All objects that need to be updated should implement this interface.
 * This interface is used in the GameWindow class.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public interface Updatable {
  /**
  * Updates the object.
  */
  void update();
}

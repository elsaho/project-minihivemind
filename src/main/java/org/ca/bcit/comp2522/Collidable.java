package org.ca.bcit.comp2522;

/**
 * Collidable. The interface for all objects that can collide with each other,
 * and provides the method that checks whether the object has collided with
 * another object.
 *
 * @author Mai Vu
 * @version 2023
 */
public interface Collidable {

  public boolean collided();
}

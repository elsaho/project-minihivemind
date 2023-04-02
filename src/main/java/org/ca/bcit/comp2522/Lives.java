package org.ca.bcit.comp2522;

/**
 * Lives. Singleton class that keeps track of the number of lives the player has.
 *
 * @author Troy Calaquian, Mai Vu
 * @version 2023
 */
public class Lives {

  private static Lives single_instance = null;
  private int lives;

  private Lives() {
    this.lives = 3;
  }

  /**
   * Gets the instance of the Lives class.
   *
   * @return the instance of the Lives class
   */
  public static Lives getInstance() {
    if (single_instance == null) {
      single_instance = new Lives();
    }
    return single_instance;
  }

  /**
   * Gets lives.
   *
   * @return lives as an int
   */
  public int getLives() {
    return lives;
  }

  /**
   * Sets lives.
   *
   * @param lives as an int
   */
  public void setLives(int lives) {
    this.lives = lives;
  }

  /**
   * Removes one life.
   */
  public void loseLife() {
    lives--;
  }

  /**
   * Adds one life.
   */
  public void gainLife() {
    //implement in future power-up class
    lives++;
  }

}
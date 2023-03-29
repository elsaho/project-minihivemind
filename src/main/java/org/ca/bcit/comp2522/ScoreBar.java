package org.ca.bcit.comp2522;

/**
 * ScoreBar. Singleton class that keeps track of the player's score.
 *
 * @author Troy Calaquian
 * @version 2023
 */
public class ScoreBar {

  private static ScoreBar single_instance = null;
  private int value;
  private ScoreBar() {
    this.value = 0;
  }

  /**
   * Gets the instance of the ScoreBar class.
   *
   * @return the instance of the ScoreBar class
   */
  public static ScoreBar getInstance() {
    if (single_instance == null) {
      single_instance = new ScoreBar();
    } else {
      single_instance.resetValue();
    }
    return single_instance;
  }

  /**
   * Add to scoreboard value based on time
   * @param time as an int
   */
  public void finishedLevel(int time) {
    this.value += (100 * time);
  }

  /**
   * Gets score value
   * @return value as an int
   */
  public int getValue() {
    return value;
  }

  /**
   * Resets score value
   */
  public void resetValue() {
    this.value = 0;
  }

  /**
   * Adds to score value
   * @param i as an int
   */
  public void addScore(int i) {
    this.value += i;
  }
}

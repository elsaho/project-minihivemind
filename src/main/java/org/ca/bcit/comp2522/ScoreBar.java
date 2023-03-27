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

  public void poppedBubble(int size) {
    //Add to scoreboard value based on bubble size
    this.value += (300 - ((size - 1) * 50));
  }

  public void finishedLevel(int time) {
    //Add to scoreboard value based on time
    this.value += (100 * time);
  }

  public int getValue() {
    return value;
  }

  public void resetValue() {
    this.value = 0;
  }

  public void addScore(int i) {
    this.value += i;
  }

  public void reset() {
    resetValue();
  }
}

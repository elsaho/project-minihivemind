package org.ca.bcit.comp2522;

public class ScoreBar {

  private static ScoreBar single_instance = null;
  private int value;

  public ScoreBar() {
    this.value = 0;
  }

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
}

package org.ca.bcit.comp2522;

/**
 * Timer to keep track in game time.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class Timer {
  /**
   * Start time var
   */
  private long start;
  /**
   * Remaining time in game.
   */
  private long remaining;
  /**
   * Singleton check.
   */
  private static Timer single_instance = null;

  /**
   * Initiates singleton Timer
   * @return single_instance Timer
   */
  public static Timer getInstance() {
    if (single_instance == null) {
      single_instance = new Timer();
    }
    return single_instance;
  }

  /**
   * Getter for start time.
   * @return start long
   */
  public long getStart() {
    return start;
  }

  /**
   * Setter start time.
   * @param start long
   */
  public void setStart(long start) {
    this.start = start;
  }

  /**
   * Getter for remaining time.
   * @return remaining long
   */
  public long getRemaining() {
    return remaining;
  }

  /**
   * Setter for remaining time, initalized at game start.
   * @param remaining long
   */
  public void setRemaining(long remaining) {
    this.remaining = remaining;
  }

  /**
   * converts current time in seconds to String.
   * @return timeString String
   */
  public String timeToString() {
    int seconds = (int) (single_instance.remaining / 1000);
    return String.format("%02d", seconds);
  }

}

package org.ca.bcit.comp2522;

public class Timer {

  private long start;
  private long remaining;

  private static Timer single_instance = null;
  public static Timer getInstance() {
    if (single_instance == null) {
//      single_instance.start = System.currentTimeMillis() + 90000;
      single_instance = new Timer();
    }
    return single_instance;
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public long getRemaining() {
    return remaining;
  }

  public void setRemaining(long remaining) {
    this.remaining = remaining;
  }

  public void updateTimer() {
    single_instance.remaining = single_instance.start - System.currentTimeMillis();
  }

  public String timeToString() {
    int seconds = (int) (single_instance.remaining / 1000);
    String timeString = String.format("%02d", seconds);
    return timeString;
  }


}

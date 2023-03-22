package org.ca.bcit.comp2522;

public class Lives {

  private static Lives single_instance = null;
  private int lives;

  public Lives() {
    this.lives = 3;
  }

  public static Lives getInstance() {
    if (single_instance == null) {
      single_instance = new Lives();
    } else {
      single_instance.setLives(3);
    }
    return single_instance;
  }

  public int getLives() {
    return lives;
  }

  public void setLives(int lives) {
    this.lives = lives;
  }

  public void loseLife() {
    lives--;
  }

  public void gainLife() {
    lives++;
  }
}

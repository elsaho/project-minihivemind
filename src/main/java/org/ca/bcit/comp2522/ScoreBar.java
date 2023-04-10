package org.ca.bcit.comp2522;

import processing.core.PFont;
import processing.core.PImage;

/**
 * ScoreBar. Singleton class that keeps track of the player's score.
 *
 * @author Troy Calaquian, Mai Vu, Haurence Li, Tomek Stojek, and Elsa Ho
 * @version 2023
 */
public class ScoreBar implements Displayable {

  private static ScoreBar single_instance = null;
  private int value;

  private Lives lives;
  private Timer timer;

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
    }
    return single_instance;
  }

  /**
   * Add to scoreboard value based on time.
   *
   * @param time as an int
   */
  public void finishedLevel(int time) {
    this.value += (100 * time);
  }

  /**
   * Gets score value.
   *
   * @return value as an int
   */
  public int getValue() {
    return value;
  }

  /**
   * Resets score value.
   */
  public void resetValue() {
    this.value = 0;
  }

  /**
   * Adds to score value.
   *
   * @param i as an int
   */
  public void addScore(int i) {
    this.value += i;
  }

  /** Displays the score bar on the screen.
   *
   * @param window the GameWindow object
   */
  @Override
  public void display(final GameWindow window) {
    PImage heart = window.loadImage("../assets/pixelHeart.png");
    lives = Lives.getInstance();
    timer = Timer.getInstance(window);
    timer.setRemaining(timer.getStart() - window.millis());

    //Creates font
    PFont myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 18);

    //Text for lives
    Text livesText = new Text("Lives: ", 20, 60, myFont);
    livesText.display(window);
    for (int i = 0; i < lives.getLives(); i++) {
      window.image(heart, 130 + (60 * i), 30, 50, 50);
    }

    //Text for timer
    Text timerText = new Text("Time: " + timer.timeToString(), 345, 60, myFont);
    timerText.display(window);

    //Text for score tracker
    Text scoreText = new Text("Score: " + this.getValue(), 540, 60, myFont);
    scoreText.display(window);
  }

  /**
   * Updates the score bar.
   *
   * @param bubble the Bubble object
   * @param popped whether the bubble has been popped
   * @param isHit whether the bubble has been hit
   */
  public void update(Bubble bubble, boolean popped, boolean isHit) {
    if (popped) {
      this.addScore((int) (bubble.size.x * timer.getRemaining() / 10000));
    }
    if (isHit) {
      lives.loseLife();
    }
  }
}
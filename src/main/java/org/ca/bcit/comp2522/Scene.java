package org.ca.bcit.comp2522;


import processing.core.PImage;
import processing.core.PVector;
import static processing.core.PConstants.CENTER;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Scene class. The class that contains all the sprites in the game.
 * This class is responsible for updating and displaying all the sprites.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class Scene {

  InputHandler inputHandler;

  /**
   * Player constants.
   */
  private final PVector playerSize = new PVector(42, 64);
  private final int playerSpeed = 5;

  /**
   * Bubble constants.
   */
  private final PVector bubbleStartSize = new PVector(100, 100);
  private final int bubbleStartSpeed = 5;

  /**
   * Gameplay constants.
   */
  private Lives lives;

  private Timer timer;

  /**
   * Gameplay.
   */
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
  public static SoundEffects sounds;
  public static ShootLine shootLine;
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private Bubble bubble;

  private PImage bg;

  private ArrayList<Sprite> removedSprites;
  public static long lastCollisionTime = 0;
  private final int time = 90000;
  public static boolean isImmune = false;

  /**
   * Scorebar.
   */
  private ScoreBar scoreBar;


  /**
   * Game state.
   */
  public static boolean isGameOver = false;
  public static boolean isVictory = false;

  /**
   * Constructor for objects of class Scene.
   *
   * @param window as a GameWindow
   */
  public Scene(GameWindow window) throws LineUnavailableException, FileNotFoundException {
    inputHandler = window.inputHandler;
    sprites = new ArrayList<>();
    player = Player.getInstance(
        new PVector(GameWindow.getX() / 2, GameWindow.getY() - playerSize.y),
        new PVector(0, 1), playerSize, playerSpeed,
        new Color(0, 255, 255), window
    );


    try {
      sounds = new SoundEffects();
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }

    bubbles = new ArrayList<>();
    removedSprites = new ArrayList<>();
    //generate random position for the bubble to start dropping
    Random rand = new Random();
    int bubbleStartX = 700;
    int bubbleStartY = rand.nextInt(100) + 100;

    bubble = new Bubble(
        new PVector(bubbleStartX, bubbleStartY),
        new PVector(1, 1),
        bubbleStartSize,
        bubbleStartSpeed,
        new Color(0, 0, 255), window,
        new PVector(2, 5)
    );
    bubbles.add(bubble);
    this.scoreBar = ScoreBar.getInstance();
    this.timer = Timer.getInstance();
    this.lives = Lives.getInstance();

    //saves the score 0
    if (databaseHelper != null) {
      databaseHelper.put("score", 0);
    }
  }

  /**
   * Loads up the game and resets everything.
   */
  public void setup(GameWindow window) {
    sprites.add(player);

    for (Bubble bubble : bubbles) {
      bubble.setup(window);
      sprites.add(bubble);
    }

    /* If you want to change the image,
     * you must make the image the exact size of the window (800 x 600)
     */
    bg = window.loadImage("../assets/SkyBackground.png");

    //starts the timer
    timer.setStart(window.millis() + time);
  }

  /**
   * Displays the game.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);

    for (Sprite sprite : sprites) {
      sprite.display(window);
    }

    // Displays the scoreBar
    scoreBar.display(window);
  }

  /**
   * Updates the game.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    player.update(window);
    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Bubble bubble : bubbles) {
      bubble.bounce();
      bubble.display(window);
      if (bubble.collided(player) && !isImmune) {
        bubble.update(window, bubble);
      }

      // Check if the player's immunity has expired
      if (isImmune && System.currentTimeMillis() - lastCollisionTime > 1500) {
        isImmune = false;
      }

      if (player.getPlayersLine() != null && bubble.collided(player.getPlayersLine())) {
        sounds.playPop();
        player.setPlayersLine(null);
        bubblesToRemove.add(bubble);
        if (bubble.size.x > bubble.MIN_SIZE) {
          newBubbles.addAll(bubble.spawnBubbles());
        }
        //update score
        scoreBar.update(window, bubble, true, false );

        if (databaseHelper != null) {
          //save score to database everytime bubble is popped
          databaseHelper.put("score", scoreBar.getValue());
        }
      }
    }

    if (timer.getRemaining() <= 0 || lives.getLives() <= 0) {
      isGameOver = true;
      if (databaseHelper != null) {
        databaseHelper.put("score", scoreBar.getValue());
      }
      System.out.println("Final score is: " + scoreBar.getValue());
    }

    //Remove bubbles that have been popped, and add new bubbles
    bubbles.removeAll(bubblesToRemove);
    bubbles.addAll(newBubbles);
    removedSprites.addAll(bubblesToRemove);
    for (Sprite sprite : removedSprites) {
      sprites.remove(sprite);
    }

    //Game victory
    if (bubbles.isEmpty()) {
      sounds.playWinAudio();
      scoreBar.finishedLevel((int) timer.getRemaining() / 10000);
      scoreBar.addScore(lives.getLives() * 1000);
      System.out.println("Final score is: " + scoreBar.getValue()); //for test
      if (databaseHelper != null) {
        databaseHelper.put("score", scoreBar.getValue());
      }
      isVictory = true;
    }
  }

  /** Methods for testing purposes. */
  public InputHandler getInputHandler() {
    return inputHandler;
  }

  public SoundEffects getSounds() {
    return sounds;
  }

  public static ShootLine getShootLine() {
    return shootLine;
  }

  public Player getPlayer() {
    return player;
  }

  public ArrayList<Sprite> getSprites() {
    return sprites;
  }

  public Bubble getBubble() {
    return bubble;
  }

  public ArrayList<Sprite> getRemovedSprites() {
    return removedSprites;
  }

  public Lives getLives() {
    return lives;
  }

  public Timer getTimer() {
    return timer;
  }

  public ScoreBar getScoreBar() {
    return scoreBar;
  }

  public PImage getBg() {
    return bg;
  }

  public ArrayList<Bubble> getBubbles() {
    return bubbles;
  }

}
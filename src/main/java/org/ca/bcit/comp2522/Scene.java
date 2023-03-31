package org.ca.bcit.comp2522;


import processing.core.PImage;
import processing.core.PVector;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static processing.core.PConstants.UP;

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
  private final int playerSize = 64;
  private final int playerSpeed = 5;

  /**
   * Bubble constants.
   */
  private final int bubbleStartSize = 100;
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
  protected SoundEffects sounds;
  public static ShootLine shootLine;
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private Bubble bubble;

  private PImage bg;

  private ArrayList<Sprite> removedSprites;
  private long lastCollisionTime = 0;
  private final int time = 90000;
  private boolean isImmune = false;

  /**
   * Scorebar.
   */
  private ScoreBar scoreBar;


  /**
   * Game state.
   */
  public boolean isGameOver = false;
  public boolean isVictory = false;

  /**
   * Constructor for objects of class Scene.
   *
   * @param window as a GameWindow
   */
  public Scene(GameWindow window) {
    inputHandler = window.inputHandler;
    sprites = new ArrayList<>();
    player = new Player(
        new PVector(GameWindow.getX() / 2, GameWindow.getY() - playerSize),
          new PVector(0, 1), playerSize, playerSpeed,
        new Color(0, 255, 255), window
    );

    shootLine = null;

    try {
      sounds = new SoundEffects();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (LineUnavailableException e) {
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
    databaseHelper.put("score", 0);
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
   * Creates a shootLine to shoot bubbles.
   *
   * @param window game window
   */
  void UpdateLineInstance(GameWindow window) {
    if (shootLine == null) {
      if (window.keyPressed) {
        if (window.keyCode == UP) {
          shootLine = new ShootLine(
              new PVector(player.position.x + player.size / 2, player.position.y),
              player.direction, player.size, playerSpeed,
              new Color(0, 255, 255), window
          );
          sounds.playShoot();
        }
      }
    }
  }

  /**
   * Displays the game.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);
    window.fill(0);
    window.rect(0, 0, GameWindow.getX(), 100);

    for (Sprite sprite : sprites) {
      sprite.display(window);
    }
    if (shootLine != null) {
      shootLine.display(window);
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
    if (shootLine != null) {
      shootLine.update(window);
    }

    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Bubble bubble : bubbles) {
      bubble.bounce();
      bubble.display(window);

      if (bubble.collided(player)) {
        if (!isImmune) {
          if (lives.getLives() > 0) {
            sounds.playOof();
            scoreBar.update(window, bubble, false, true);
            System.out.println("You lost a life " + lives.getLives());
            isImmune = true;
            lastCollisionTime = System.currentTimeMillis();
          } else {
            sounds.playLoseAudio();
            isGameOver = true;
          }
        }
      }

      // Check if the player's immunity has expired
      if (isImmune && System.currentTimeMillis() - lastCollisionTime > 1500) {
        isImmune = false;
      }

      if (shootLine != null && bubble.collided(shootLine)) {
        sounds.playPop();
        shootLine = null;
        bubblesToRemove.add(bubble);
        if (bubble.size > bubble.MIN_SIZE) {
          newBubbles.addAll(bubble.spawnBubbles());
        }
        //update score
        scoreBar.update(window, bubble, true, false );

        //save score to database everytime bubble is popped
        databaseHelper.put("score", scoreBar.getValue());

        System.out.println("You popped a bubble!");
      }
    }

    if (timer.getRemaining() <= 0 || lives.getLives() <= 0) {
      isGameOver = true;
    }

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
      isVictory = true;
    }
  }

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
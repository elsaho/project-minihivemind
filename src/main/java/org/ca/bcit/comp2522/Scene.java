package org.ca.bcit.comp2522;


import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;

import processing.core.*;

import static processing.core.PConstants.UP;

/**
 * Scene class. The class that contains all the sprites in the game.
 * This class is responsible for updating and displaying all the sprites.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class Scene {
  /**
   * Player constants
   */
  private final int playerSize = 64;
  private final int playerSpeed = 5;

  /**
   * Bubble constants
   */
  private final int bubbleStartSize = 100;
  private final int bubbleStartSpeed = 5;

  /**
   * Gameplay constants
   */
  private final int time = 90000; //length of game in milliseconds

  /**
   * Gameplay.
   */
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
  protected SoundEffects sounds;
  public static Line line;
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private Bubble bubble;
  private PImage bg;
  private PImage heart;
  private PFont myFont;
  private ArrayList<Sprite> removedSprites;
  private long lastCollisionTime = 0;
  private boolean isImmune = false;

  /**
   * Scorebar and timer.
   */
  private ScoreBar scoreBar;
  private Lives lives;
  private Timer timer;

  /**
   * Game state
   */
  public boolean isGameOver = false;
  public boolean isVictory = false;

  /**
   * Constructor for objects of class Scene.
   *
   * @param window as a GameWindow
   */
  public Scene(GameWindow window) {
    sprites = new ArrayList<>();
    player = new Player(
        new PVector(GameWindow.getX() / 2, GameWindow.getY() - playerSize),
          new PVector(0, 1), playerSize, playerSpeed,
        new Color(0, 255, 255), window
    );

    line = null;
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
    lives = Lives.getInstance();

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

    myFont = window.createFont("../assets/PressStart2P-Regular.ttf", 18);

    bg = window.loadImage("../assets/SkyBackground.png");
    heart = window.loadImage("../assets/pixelHeart.png");

    lives = Lives.getInstance();
    scoreBar = ScoreBar.getInstance();

    timer = Timer.getInstance();
    timer.setStart(window.millis() + time);
  }

  /**
   * Creates a line to shoot bubbles.
   *
   * @param window game window
   */
  void UpdateLineInstance(GameWindow window) {
    if (line == null) {
      if (window.keyPressed) {
        if (window.keyCode == UP) {
          line = new Line(
              new PVector(player.position.x, player.position.y),
              player.direction, player.size, playerSpeed,
              new Color(0, 255, 255), window
          );
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
    if (line != null) {
      line.display(window);

    }

    timer.setRemaining(timer.getStart() - window.millis());

    window.fill(255, 255, 255);
    window.textFont(myFont);
    window.textAlign(PConstants.LEFT);
    window.text("Lives: ", 10, 55);
    for (int i = 0; i < lives.getLives(); i++) {
      window.image(heart, 110 + (60 * i), 25, 50, 50);
    }

    window.text("Time: " + timer.timeToString(), 350, 55);
    window.text("Score: " + scoreBar.getValue(), 550, 55);

  }

  /**
   * Updates the game.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) {
    player.update(window);
    if (line != null) {
      line.update(window);
    }

    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Bubble bubble : bubbles) {
      bubble.bounce();
      bubble.display(window);

      if (Sprite.collided(bubble, player)) {
        if (!isImmune) {
          if (lives.getLives() > 0) {
            sounds.playOof();
            lives.loseLife();
            System.out.println("You lost a life");
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

      if (line != null && Sprite.collided(line, bubble)) {

        sounds.playPop();
        line = null;
        bubblesToRemove.add(bubble);
        if (bubble.size > bubble.MIN_SIZE) {
          newBubbles.addAll(bubble.spawnBubbles());
        }

        scoreBar.addScore((int) (bubble.size * timer.getRemaining() / 10000));

        //save score to database everytime bubble is popped
        databaseHelper.put("score", scoreBar.getValue());

        System.out.println("You popped a bubble!");
      }
    }

    if (timer.getRemaining() <= 0) {
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
      scoreBar.finishedLevel((int) timer.getRemaining() / 10000);
      isVictory = true;
    }
  }

}
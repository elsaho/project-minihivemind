package org.ca.bcit.comp2522;


import processing.core.PConstants;
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
   * Constants.
   */
  private final int playerSize = 64;

  /**
   * Gameplay.
   */

  protected SoundEffects sounds;
  public static Line line;
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private Bubble bubble;
  private PImage bg;
  private PImage heart;
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
   * Other.
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
          new PVector(0, 1), playerSize, 5,
        new Color(0, 255, 255), window
    );


    line = null;
    try {
      sounds = new SoundEffects();
      sounds.playBGM();
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
        100,
        5,
        new Color(0, 0, 255), window,
        new PVector(2, 5)
    );
    bubbles.add(bubble);
    lives = Lives.getInstance();
  }

  /**
   * Loads up the game and resets everything.
   */
  public void setup(GameWindow window) {
    for (Bubble bubble : bubbles) {
      bubble.setup(window);
    }
    sprites.add(player);
    for (Bubble bubble : bubbles) {
      sprites.add(bubble);
    }

    /* If you want to change the image,
     * you must make the image the exact size of the window (800 x 600)
     */
    bg = window.loadImage("../assets/SkyBackground.png");
    heart = window.loadImage("../assets/pixelHeart.png");

    lives = Lives.getInstance();
    scoreBar = ScoreBar.getInstance();

    timer = Timer.getInstance();
    timer.setStart(window.millis() + 90000);
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
              player.direction, player.size, 5,
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
    window.textSize(32);
    window.textAlign(PConstants.LEFT);
    window.text("Lives: ", 20, 55);
    for (int i = 0; i < lives.getLives(); i++) {
      window.image(heart, 110 + (60 * i), 25, 50, 50);
    }
    window.text("Time: " + timer.timeToString(), 350, 55);
    window.text("Score: " + scoreBar.getValue(), 600, 55);
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
            lives.loseLife();
            System.out.println("You lost a life");
            isImmune = true;
            lastCollisionTime = System.currentTimeMillis();
          } else {
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
        System.out.println("You popped a bubble!");
      }

      if (timer.getRemaining() <= 0) {
        isGameOver = true;
      }
    }

    bubbles.removeAll(bubblesToRemove);
    bubbles.addAll(newBubbles);

    removedSprites.addAll(bubblesToRemove);

    for (Sprite sprite : removedSprites) {
      sprites.remove(sprite);
    }

    //Game victory
    if (bubbles.isEmpty()) {
      isVictory = true;
    }
  }

}
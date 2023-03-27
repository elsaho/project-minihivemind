package org.ca.bcit.comp2522;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import javax.sound.sampled.LineUnavailableException;

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
   * Constants
   */
  private final int playerSize = 64;

  /**
   * Gameplay
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

  /**
   * Scorebar and timer
   */
  private ScoreBar scoreBar;
  private Lives lives;
  private Timer timer;

  /**
   * Other
   */
  public boolean isGameOver = false;

  /**
   * Constructor for objects of class Scene.
   *
   * @param window as a GameWindow
   */
  public Scene(GameWindow window) {
    sprites = new ArrayList<>();
    player = new Player(
        new PVector(GameWindow.getX() / 2, GameWindow.getY() - playerSize),
        new PVector(0, 1), playerSize, 5,
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
    bubble = new Bubble(
        new PVector(400, 100),
        new PVector(1, 1),
        100,
        5,
        new Color(0, 0, 255), window,
        new PVector(2, 5)
    );
    bubbles.add(bubble);
    lives = new Lives();
  }

  /**
   * Loads up the game and resets everything.
   */
  public void setup(PApplet parent) {
    for (Bubble bubble : bubbles) {
      bubble.setup(parent);
    }
    sprites.add(player);
    for (Bubble bubble : bubbles) {
      sprites.add(bubble);
    }

    /* If you want to change the image,
     * you must make the image the exact size of the window (800 x 600)
     */
    bg = parent.loadImage("../assets/SkyBackground.png");
    heart = parent.loadImage("../assets/pixelHeart.png");

    lives = Lives.getInstance();
    scoreBar = ScoreBar.getInstance();

    timer = Timer.getInstance();
    timer.setStart(parent.millis() + 90000);
  }

  /**
   * Creates a line to shoot bubbles
   * @param window
   */
  void UpdateLineInstance(GameWindow window) {
    if(line == null) {
      if(window.keyPressed) {
        if(window.keyCode == UP) {
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
   * @param parent as a PApplet
   */
  public void display(PApplet parent) {
    parent.background(bg);
    parent.fill(0);
    parent.rect(0, 0, GameWindow.getX(), 100);


    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
    if(line != null) {
      line.display(parent);

    }

    timer.setRemaining(timer.getStart() - parent.millis());

    parent.fill(255, 255, 255);
    parent.textSize(32);
    parent.textAlign(PConstants.LEFT);
    parent.text("Lives: ", 20, 55);
    for (int i = 0; i < lives.getLives(); i++) {
      parent.image(heart, 110 + (60 * i), 25, 50, 50);
    }
    parent.text("Time: " + timer.timeToString(), 350, 55);
    parent.text("Score: " + scoreBar.getValue(), 600, 55);
  }

  long lastCollisionTime = 0;
  boolean isImmune = false;
  public void update(PApplet parent) {
    player.update(parent);
    if(line != null) {
      line.update(parent);
    }

    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Bubble bubble : bubbles) {
      bubble.bounce();
      bubble.display(parent);




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

      if(line != null && Sprite.collided(line, bubble)) {
        sounds.playPop();
        line = null;
        bubblesToRemove.add(bubble);
        if (bubble.size > 25) {
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

    for(Sprite sprite : removedSprites) {
      sprites.remove(sprite);
    }
  }

  /**
   * Resets the game if a life is lost.
   */
  public void reset() {
  }


}
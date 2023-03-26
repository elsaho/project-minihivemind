package org.ca.bcit.comp2522;

import java.awt.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PConstants.UP;

public class Scene {

  public static Line line;
  private final Player player;
  private int playerSize = 64;
  private Lives lives;
  private ArrayList<Sprite> sprites;
  private ArrayList<Bubble> bubbles;


  private Bubble bubble;
  private PImage bg;
  private PImage heart;
  private ScoreBar scoreBar;
  private long start;
  private long remaining;

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

    bubbles = new ArrayList<>();
    bubble = new Bubble(
        new PVector(400, 00),
        new PVector(0, 1),
        100,
        5,
        new Color(0, 0, 255), window,
        new PVector(0, 5)
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

    start = parent.millis() + 90000;
  }

  void UpdateLineInstance(GameWindow window) {
    if(line == null) {
      if(window.keyPressed) {
        if(window.keyCode == UP) {
          line = new Line(
              new PVector(player.position.x, window.getY()),
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

    remaining = start - parent.millis();
    String timeString = parent.nf((int) (remaining / 1000), 2);

    parent.fill(255, 255, 255);
    parent.textSize(32);
    parent.textAlign(PConstants.LEFT);
    parent.text("Lives: ", 20, 55);
    for (int i = 0; i < lives.getLives(); i++) {
      parent.image(heart, 110 + (60 * i), 25, 50, 50);
    }
    parent.text("Time: " + parent.nf((int) (remaining / 1000), 2), 350, 55);
    parent.text("Score: " + scoreBar.getValue(), 600, 55);
  }

  /**
   * Updates the game. This includes checking for collisions and resetting the game.
   *
   * @param parent as a PApplet
   */
  public void update(PApplet parent) {
    player.update(parent);
    if(line != null) {
      line.update(parent);
    }
    for (Bubble bubble : bubbles) {
      bubble.bounce();


      if (Sprite.collided(bubble, player)) {
        if (lives.getLives() > 0) {
          lives.loseLife();
          reset();
          System.out.println("You lost a life");
        } else {
          //Game Over, need to implement
          //For now, this is to confirm that the game is over
          System.out.println("Game Over!");
        }

      }
      if(line != null && Sprite.collided(line, bubble)) {
//        line = null;
        bubbles.remove(bubble);
//        sprites.remove(bubble);
//      scoreBar.addScore(100);
        System.out.println("You popped a bubble!");

      }

      if (remaining <= 0) {
        //Game Over, need to implement
        //For now, this is to confirm that the game is over
        System.out.println("Game Over!");
      }
    }
  }

  /**
   * Resets the game if a life is lost.
   */
  public void reset() {
    player.position = new PVector(GameWindow.getX()/2, GameWindow.getY() - 64);
    bubbles.clear();
    bubble.position = new PVector(400, 50);
    bubbles.add(bubble);
  }

  public Player getPlayer() {
    return player;
  }


}

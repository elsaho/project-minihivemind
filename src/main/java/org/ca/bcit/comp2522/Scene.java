package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PConstants.UP;

public class Scene {

  private final Player player;
  private int playerSize = 64;
  private Lives lives;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private Bubble bubble;
  private PImage bg;
  private PImage heart;
  private ScoreBar scoreBar;
  private long start;
  private long remaining;

  public static Line line;

  public Scene(GameWindow window){
    sprites = new ArrayList<>();
    int playerSize = 100;
    player = new Player(
        new PVector(GameWindow.getX()/2, GameWindow.getY() - playerSize),
        new PVector(0, 1), playerSize, 5,
        new Color(0, 255, 255), window
    );
    line = null;

    bubbles = new ArrayList<>();
    bubble = new Bubble(
        new PVector(400, 50),
        new PVector(0, 1),
        100,
        5,
        new Color(0, 0, 255), window,
        new PVector(0, 5)
    );
    bubbles.add(bubble);
    lives = new Lives();
  }

  public void setup(PApplet parent) {
    for (Bubble bubble: bubbles) {
      bubble.setup(parent);
    }
    sprites.add(player);
    for (Bubble bubble: bubbles) {
      sprites.add(bubble);
    }

    // If you want to change the image, you must make the image the exact size of the window (800 x 600)
    bg = parent.loadImage("../assets/SkyBackground.png");
    heart = parent.loadImage("../assets/pixelHeart.png");

    lives = Lives.getInstance();
    scoreBar = ScoreBar.getInstance();

    start = parent.millis() + 90000;
;  }

  public void display(PApplet parent) {
    parent.background(255);

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
    parent.text("Time: " + timeString, 350, 55);
    parent.text("Score: " + scoreBar.getValue(), 600, 55);
  }

  void MakeLineInstance(GameWindow window) {
    if(line == null && window.keyPressed && window.keyCode == UP) {
      line = new Line(
          this.player.getPosition(),
          new PVector(0, 1),
          100,
          5,
          new Color(0, 0, 255),
          window,
          new PVector(0, 5)
      );
    }
  }

  public void update(PApplet parent) {
    player.update(parent);
    for (Bubble bubble: bubbles) {
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

      if (remaining <= 0) {
        //Game Over, need to implement
        //For now, this is to confirm that the game is over
        System.out.println("Game Over!");
      }
    }
    if(line != null) {
      line.update(parent, player);
    }

  }

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

package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PVector;

public class Scene {
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;

  public Scene(GameWindow window){
    sprites = new ArrayList<>();
    int playerSize = 100;
    player = new Player(
        new PVector(GameWindow.getX()/2, GameWindow.getY() - playerSize),
        new PVector(0, 1), playerSize, 5,
        new Color(0, 255, 255), window
    );

    bubbles = new ArrayList<>();
    Bubble bubble = new Bubble(
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
  }

  public void display(PApplet parent) {
    parent.background(255);

    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
  }

  public void update(PApplet parent) {
    player.update(parent);
    for (Bubble bubble: bubbles) {
      bubble.bounce();

      if (Sprite.collided(player, bubble)) {
        if (lives.getLives() > 0) {
          lives.loseLife();
          System.out.println("You lost a life");
//          new Scene(this);
          //reset game
        } else {
          //game over
        }

      }
    }
  }

  public Player getPlayer() {
    return player;
  }
}

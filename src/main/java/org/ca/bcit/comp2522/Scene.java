package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Scene extends PApplet{
  private PApplet parent;
  private Player player;
  private Bubble bubble;
  ArrayList<Sprite> sprites;
  ArrayList<Bubble> bubbles;
  Lives lives;


  public Scene(PApplet parent) {
    this.parent = parent;
    sprites = new ArrayList<Sprite>();
    player = new Player(
        new PVector(this.width/2,460),

        new PVector(0,1),
        40,
        5,
        new Color(0,255,255), this );

    bubbles = new ArrayList<Bubble>();
    bubble = new Bubble(
            new PVector(GameWindow.getX()/2,50),
            new PVector(0,1),
            100,
            5,
            new Color(0,0,255),
            this,
            new PVector (0, 5));
    bubbles.add(bubble);
    lives = new Lives();
  }

  public void display() {
    parent.background(255);
    sprites.add(player);
    for (Bubble bubble: bubbles) {
      sprites.add(bubble);
    }
    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
  }

  public void update() {
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

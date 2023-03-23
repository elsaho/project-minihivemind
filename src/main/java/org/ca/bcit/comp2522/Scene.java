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


  public Scene(PApplet parent) {
    this.parent = parent;
    sprites = new ArrayList<Sprite>();
    player = new Player(
        new PVector(this.width/2,460),
        new PVector(0,1),
        40,
        5,
        new Color(0,255,255), this );
    bubble = new Bubble( //make this array later
            new PVector(GameWindow.getX()/2,50),
            new PVector(0,1),
            100,
            5,
            new Color(0,0,255),
            this,
            new PVector (0, 5));
  }

  public void display() {
    parent.background(255);
    sprites.add(player);
    sprites.add(bubble);
    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
  }

  public void update() {
    player.update(parent);
    bubble.bounce();
  }

  public Player getPlayer() {
    return player;
  }
}

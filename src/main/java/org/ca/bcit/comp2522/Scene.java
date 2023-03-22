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
  ArrayList<Sprite> sprites;

  public Scene(PApplet parent) {
    this.parent = parent;
    sprites = new ArrayList<Sprite>();
    player = new Player(
        new PVector(this.width/2,490),
        new PVector(0,1),
        100,
        2,
        new Color(0,255,0), this );
  }

  public void display() {
    parent.background(255);
    sprites.add(player);
    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
  }

  public void update() {
    player.update(parent);
  }

  public Player getPlayer() {
    return player;
  }
}

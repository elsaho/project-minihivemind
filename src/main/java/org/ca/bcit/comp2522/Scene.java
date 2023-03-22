package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PApplet;

public class Scene {
  private PApplet parent;
  private Player player;


  public Scene(PApplet parent) {
    this.parent = parent;
//    player = new Player(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    player = new Player((GameWindow.getX() - 50)/ 2, GameWindow.getY() - 50, 50, 50);
  }

  public void display() {
    parent.background(255);
    player.display(parent);
  }

  public void update() {
    player.update(parent);
  }

  public Player getPlayer() {
    return player;
  }
}

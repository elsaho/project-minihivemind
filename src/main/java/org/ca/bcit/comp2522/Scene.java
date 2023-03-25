package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;

public class Scene {
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;
  private PImage bg;

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
    bg = parent.loadImage("../assets/test.png");
  }

  public void display(PApplet parent) {
    parent.background(bg);

    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
  }

  public void update(PApplet parent) {
    player.update(parent);
    for (Bubble bubble: bubbles) {
      bubble.bounce();
    }
  }

  public Player getPlayer() {
    return player;
  }

}

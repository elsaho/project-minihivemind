package org.ca.bcit.comp2522;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PVector;

public class Scene {
  private final Player player;
  private final ArrayList<Sprite> sprites;
  private final ArrayList<Bubble> bubbles;

  private final Line line;

  public Scene(GameWindow window){
    sprites = new ArrayList<>();
    int playerSize = 100;

    player = new Player(
        new PVector(GameWindow.getX()/2, GameWindow.getY() - playerSize),
        new PVector(0, 1), playerSize, 5,
        new Color(0, 255, 255), window
    );
    line = new Line(
        new PVector(400, 0),
        new PVector(0, 1),
        100,
        5,
        new Color(0, 0, 255), window,
        new PVector(0, 5)
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
//    sprites.add(line);
    for (Bubble bubble: bubbles) {
      sprites.add(bubble);
    }

  }

  public void display(PApplet parent) {
    parent.background(255);

    for (Sprite sprite : sprites) {
      sprite.display(parent);
    }
    line.display(parent);

  }

  public void update(PApplet parent) {
    player.update(parent);
    for (Bubble bubble: bubbles) {
      bubble.bounce();
    }
    line.update(parent, player);
  }

  public Player getPlayer() {
    return player;
  }

}

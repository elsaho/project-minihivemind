package org.ca.bcit.comp2522;

import processing.core.PApplet;

/**
 *
 */
public class GameWindow extends PApplet {
  private Scene scene;

  public GameWindow(Scene scene) {
    this.scene = scene;
  }

  public void settings() {
    size(640, 360);
  }

  public void setup() {
    surface.setTitle("Game Window");
    frameRate(60);
    noStroke();
    fill(255);
    scene.setParent(this);
  }

  public void draw() {
    background(0);
    scene.update();
    scene.display();
  }

  public static void main(String[] args) {
    String[] appArgs = new String[] { "GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow(new Scene()));
  }

}
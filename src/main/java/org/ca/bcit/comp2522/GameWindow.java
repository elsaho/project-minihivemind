package org.ca.bcit.comp2522;

import processing.core.PApplet;

/**
 *
 */
public class GameWindow extends PApplet {
  private Scene scene;
  private static int x = 500;

  private static int y = 500;

  public static int getX() {
    return x;
  }

  public static int getY() {
    return y;
  }



  public void settings() {
    size(x, y);
  }

  public void setup() {
    scene = new Scene(this);
  }

  public void draw() {
    scene.update();
    scene.display();
  }
  
  public static void main(String[] args) {
    String[] appArgs = new String[] { "org.ca.bcit.comp2522.GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow());
  }
}
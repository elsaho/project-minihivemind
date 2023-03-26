package org.ca.bcit.comp2522;

import processing.core.PApplet;

/**
 *
 */
public class GameWindow extends PApplet {

  private Scene scene;
  private static final int x = 800;

  private static final int y = 600;

  public static int getX() {
    return x;
  }

  public static int getY() {
    return y;
  }
  private GameOver gameOver;


  public void settings() {
    size(x, y);
  }

  public void setup() {
    scene = new Scene(this); //init?
    scene.setup(this);

    gameOver = new GameOver(this);
    gameOver.setup(this);
  }

  public void draw() {
    if (scene.isGameOver) {
      gameOver.update(this);
      gameOver.display(this);
    } else {
      scene.display(this);
      scene.update(this);
    }
  }
  
  public static void main(String[] args) {
    String[] appArgs = new String[] { "org.ca.bcit.comp2522.GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow());
  }
}
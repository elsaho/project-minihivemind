package org.ca.bcit.comp2522;

import processing.core.PApplet;

/**
 *
 */
public class GameWindow extends PApplet {
  private Scene scene;

  public void settings() {
    size(500, 500);
  }

  public void setup() {
    scene = new Scene(this);
  }

  public void draw() {
    scene.update();
    scene.display();
  }

  public void keyPressed() {
    if (key == 'w') {
      scene.getPlayer().setY(scene.getPlayer().getY() - 5);
    } else if (key == 's') {
      scene.getPlayer().setY(scene.getPlayer().getY() + 5);
    }
  }

  public static void main(String[] args) {
    String[] appArgs = new String[] { "org.ca.bcit.comp2522.GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow());
  }
}
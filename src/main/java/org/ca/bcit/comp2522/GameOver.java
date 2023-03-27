package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

/**
 * GameOver. The class for the game over screen.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class GameOver extends PApplet{

  private PImage bg;
  private PImage gameOverText;
  private Button restart;

  public GameOver(GameWindow window) {

  }

  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/NightBackground.png");
    gameOverText = parent.loadImage("../assets/gameover.png");
    restart = new Button(500, 500, 100, 50, "Restart");
//    noLoop();
  }

  public void display(PApplet parent) {
    parent.background(bg);
    parent.image(gameOverText, (parent.width - gameOverText.width) / 2, (parent.height - gameOverText.height) / 2,
            100, 100);
    restart.display(parent);
  }


  public void update(PApplet parent) {
    if (restart.isClicked(parent.mouseX, parent.mouseY, parent.mousePressed)) {
      System.out.println("Restart button clicked!");
    }
  }


  public void draw() {
    background(bg);
    restart.display(this);
  }

}
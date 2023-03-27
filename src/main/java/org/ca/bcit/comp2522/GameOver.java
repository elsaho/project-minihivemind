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
  private PImage replayButton;
  private Button restart;

  public GameOver(GameWindow window) {

  }

  public void setup(PApplet parent) {
    bg = parent.loadImage("../assets/GameOverScreen.png");
    replayButton = parent.loadImage("../assets/TransparentReplay.png");
    restart = new Button(100, 323, 600, 206, replayButton);
  }

  public void display(PApplet parent) {
    parent.background(bg);
    restart.display(parent);
  }


  public void update(PApplet parent) {
    if (restart.isClicked(parent.mouseX, parent.mouseY, parent.mousePressed)) {
      System.out.println("Restart button clicked!");
      // TODO implement restart feature
    }
  }


  public void draw() {
    background(bg);
    restart.display(this);
  }
}
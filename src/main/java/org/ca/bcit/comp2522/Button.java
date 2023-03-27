package org.ca.bcit.comp2522;

import processing.core.PApplet;
import processing.core.PImage;

public class Button extends PApplet{

  private int xPos;
  private int yPos;
  private int width;
  private int height;
  private PImage img;

  public Button(int xPos, int yPos, int width, int height, PImage img) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.img = img;
  }

  public boolean isClicked(float mouseX, float mouseY, boolean mousePressed) {
    return mousePressed &&
            mouseX >= xPos &&
            mouseX <= xPos + width &&
            mouseY >= yPos &&
            mouseY <= yPos + height;
  }

  public void display(PApplet parent) {
    parent.image(img, xPos, yPos, width, height);
    parent.textAlign(PApplet.CENTER, PApplet.CENTER);
    parent.fill(0);
  }


}

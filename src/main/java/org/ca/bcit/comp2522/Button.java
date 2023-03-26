package org.ca.bcit.comp2522;

import processing.core.PApplet;

public class Button extends PApplet{

  private int xPos;
  private int yPos;
  private int width;
  private int height;
  private String text;

  public Button(int xPos, int yPos, int width, int height, String text) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.text = text;
  }

  public boolean isClicked(float mouseX, float mouseY, boolean mousePressed) {
    return mousePressed &&
            mouseX >= xPos &&
            mouseX <= xPos + width &&
            mouseY >= yPos &&
            mouseY <= yPos + height;
  }

  public void display(PApplet parent) {
    parent.fill(255, 255, 0);
    parent.rect(xPos, yPos, width, height);
  }


}

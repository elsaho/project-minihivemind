package org.ca.bcit.comp2522;

import processing.core.PApplet;

import javax.sound.sampled.LineUnavailableException;
import java.applet.*;
import java.io.FileNotFoundException;

/**
 * The main class for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
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
  private StartPage startPage;


  public void settings() {
    size(x, y);
  }

  public void setup() {
    //sound
    try {
      SoundEffects audio = new SoundEffects();
      audio.playBGM();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    scene = new Scene(this); //init?
    scene.setup(this);

    gameOver = new GameOver(this);
    gameOver.setup(this);

    startPage = new StartPage(this);
    startPage.setup(this);
  }

  public void draw() {
    if (scene.isGameOver) {
      gameOver.update(this);
      gameOver.display(this);
    } else {
      scene.display(this);
      scene.UpdateLineInstance(this);
      scene.update(this);
    }
  }
  
  public static void main(String[] args) {
    String[] appArgs = new String[] { "org.ca.bcit.comp2522.GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow());
  }
}
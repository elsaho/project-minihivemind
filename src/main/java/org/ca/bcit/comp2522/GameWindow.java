package org.ca.bcit.comp2522;

import  processing.core.PApplet;
import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;

/**
 * The main class for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class GameWindow extends PApplet {
  /** Scene class to handle game scenes */
  private Scene scene;
  /** Game screen width */
  private static final int x = 800;
  /** Game screen height */
  private static final int y = 600;
  /** Getter for screen width */
  public static int getX() {
    return x;
  }

  /** Getter for screen height */
  public static int getY() {
    return y;
  }
  /** Start page, with instructions */
  InstructionStart instructionStart;
  /** Game over page */
  private GameOver gameOver;
  /** Game Victory page */
  private GameVictory gameVictory;
  /** audio class*/
  private SoundEffects audio;

  /** Settings of game window */
  public void settings() {
    size(x, y);
  }


  /**
   * Sets up the game window
   */
  public void setup() {
    //Game sounds
    try {
      audio = new SoundEffects();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    //Game start and instructions
    instructionStart = new InstructionStart(this);
    instructionStart.setup(this);
    //Game
    scene = new Scene(this); //init?
    scene.setup(this);
    //Game lose
    gameOver = new GameOver(this);
    gameOver.setup(this);

    //Game win
    gameVictory = new GameVictory(this);
    gameVictory.setup(this);
  }

  /**
   * Draws the game window
   */
  public void draw() {
    if (!InstructionStart.gameStarted) {
      instructionStart.update(this);
      instructionStart.display(this);
      if (!audio.isBGMPlaying()) {
        audio.playBGM();
      }
    } else if (scene.isGameOver) {
      gameOver.update(this);
      gameOver.display(this);
      audio.stopBGM(); //temp fix
    } else if (scene.isVictory) {
      gameVictory.update(this);
      gameVictory.display(this);
      audio.stopBGM(); //temp fix
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
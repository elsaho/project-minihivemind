package org.ca.bcit.comp2522;

import  processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

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
  protected InputHandler inputHandler = new InputHandler(this);
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
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    //Game start and instructions
    instructionStart = new InstructionStart(this);

    //Game
    scene = new Scene(this);
    scene.setup(this);
    //Game lose
    gameOver = new GameOver(this);
    //Game win
    gameVictory = new GameVictory(this);
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

  public void keyPressed() {
    inputHandler.update(true);
  }

  public void keyReleased() {
    inputHandler.update(false);
  }

  public InstructionStart getInstructionStart() {
    return instructionStart;
  }

  public Scene getScene() {
    return scene;
  }

  public GameOver getGameOver() {
    return gameOver;
  }

  public GameVictory getGameVictory() {
    return gameVictory;
  }

  public SoundEffects getAudio() {
    return audio;
  }

  public static void EndGameDisplay(GameWindow window, PImage bg, PFont myFont, DatabaseHelper databaseHelper, Button restart) {
    window.background(bg);
    window.textFont(myFont);
    Text highScoreText = new Text("High Score: " + databaseHelper.getHighestScore() + "\n"
      + "Your Score: " + ScoreBar.getInstance().getValue(), 20, 55, myFont);
    if (databaseHelper != null) {
      highScoreText.display(window);
    }
    restart.display(window);
  }

  /**
   * Main method to run the game.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    String[] appArgs = new String[] { "org.ca.bcit.comp2522.GameWindow" };
    PApplet.runSketch(appArgs, new GameWindow());
  }
}
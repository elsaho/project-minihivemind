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
  /** Landing page */
  private LandingPage landingPage;
  /** Start page, with instructions */
  private InstructionStart instructionStart;
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

  /**
   * Sets up the game window.
   */
  public void setup() {
    this.init();
  }

  public void init() {
    //Game sounds
    try {
      audio = new SoundEffects();
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    // Landing page
    landingPage = new LandingPage(this);
    //Game start and instructions
    instructionStart = new InstructionStart(this);
    //Game
    try {
      scene = new Scene(this);
    } catch (LineUnavailableException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
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
    if (!LandingPage.gameStarted) {
      landingPage.update(this);
      landingPage.display(this);
      if (!audio.isBGMPlaying()) {
        audio.playBGM();
      }

//    if (!InstructionStart.gameStarted) {
//      instructionStart.update(this);
//      instructionStart.display(this);
//      if (!audio.isBGMPlaying()) {
//        audio.playBGM();
//      }
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
      scene.update(this);
      audio.stopBGM();
    }
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
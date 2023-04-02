package org.ca.bcit.comp2522;

import  processing.core.PApplet;
import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The main class for the game.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class GameWindow extends PApplet {

  /** Scene class to handle game scenes */
  private ArrayList<InputHandler> handlers = new ArrayList<>();
  private Scene scene;
  /** Game screen width */
  private static final int x = 800;
  /** Game screen height */
  private static final int y = 600;
  /** Enum representing game state*/
  static Screen screen = Screen.landing;

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
  private SelectMultiPlayer selectMultiPlayer;
  private Pause pause;
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
    handlers.forEach(e -> {
      e.update(true);
      //other statements
    });
  }

  public void keyReleased() {
    handlers.forEach(e -> {
      e.update(false);
      //other statements
    });
  }

  public void addInputHandler(InputHandler handler) {
    handlers.add(handler);
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
    //Game manager
    if (!Scene.isPaused) {
      GameManager.clear();
    }

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
    //Select multiplayer
    selectMultiPlayer = new SelectMultiPlayer(this);

    pause = new Pause(this);
    //Game
    scene = new Scene();
    try {
      scene.setup(this);
    } catch (LineUnavailableException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    //Game lose
    gameOver = new GameOver(this);
    //Game win
    gameVictory = new GameVictory(this);
  }

  /**
   * Draws the game window
   */
  public void draw() {
    switch(screen) {
      case landing:
        landingPage.display(this);
        landingPage.update(this);
        audio.playBGM();
        break;

      case pause:
        pause.display(this, scene);
        try {
          pause.update(this, scene);
        } catch (LineUnavailableException  | FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        break;
      case playerSelect:
        selectMultiPlayer.display(this);
        selectMultiPlayer.update(this);
        break;

      case instruction:
        instructionStart.display(this);
        instructionStart.update(this);
        audio.stopBGM();
        break;

      case win:
        gameVictory.display(this);
        gameVictory.update(this);
        audio.stopBGM(); //temp fix
        break;

      case lose:
        gameOver.display(this);
        gameOver.update(this);
        audio.stopBGM(); //temp fix
        break;

      case level1:
        scene.display(this);
        try {
          scene.update(this);
        } catch (LineUnavailableException e) {
          throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        break;

      default:
        landingPage.display(this);
        landingPage.update(this);
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
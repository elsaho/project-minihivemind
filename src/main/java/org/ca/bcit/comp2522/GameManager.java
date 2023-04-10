package org.ca.bcit.comp2522;

import processing.core.PVector;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/** GameManager class. The class that manages the game levels.
 */
public class GameManager {
  public static ArrayList<Player> players = new ArrayList<>();
  public static PVector playerSize = new PVector(42, 64);
  public static int playerSpeed = 5;

  /**
   * Bubble constants.
   */
  public static PVector bubbleStartSize = new PVector(75, 75);
  public static int bubbleStartSpeed = 5;

  private static final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  public static Player player;

  public static Player player2;
  public static ArrayList<Sprite> sprites = new ArrayList<>();
  public static ArrayList<Bubble> bubbles = new ArrayList<>();

  public static ArrayList<Sprite> removedSprites = new ArrayList<>();
  
  /** Called when the game is paused to save data to the database.
   *
   * @param window game window
   */
  public static void pause(GameWindow window) {
    if (Scene.pause.isClicked(window.mouseX, window.mouseY, window.mousePressed)) {
      Scene.isPaused = true;
      databaseHelper.saveGame();
      GameWindow.screen = Screen.pause;
      window.init();
    }
  }

  /** Called to start the game.
   *
   * @param window game window
   * @throws LineUnavailableException if the line cannot be opened due to resource restrictions
   * @throws FileNotFoundException if the file is not found
   */
  public static void level0(GameWindow window)
      throws LineUnavailableException, FileNotFoundException {

    if (Scene.isPaused) {
      databaseHelper.loadGame(GameManager.players, GameManager.bubbles);
    } else {
      if (databaseHelper != null) {
        databaseHelper.put("score", 0);
      }
      if (!GameLanding.getIs2P()) {
        //saves the score 0
        player = new Player(
                new PVector((float) GameWindow.getX() / 2 - 50, GameWindow.getY() - playerSize.y),
                new PVector(0, 1), playerSize, playerSpeed,
                new Color(0, 255, 255), window, 37, 39, 38, 1);
        players.add(player);
      } else {
        player = new Player(
                new PVector((float) GameWindow.getX() / 2 + 50, GameWindow.getY() - playerSize.y),
                new PVector(0, 1), playerSize, playerSpeed,
                new Color(0, 255, 255), window, 37, 39, 38, 1);
        players.add(player);


        player2 = new Player(
            new PVector((float) GameWindow.getX() / 2 - 175, GameWindow.getY() - playerSize.y),
            new PVector(0, 1), new PVector(56, 69), playerSpeed,
            new Color(0, 255, 255), window, 65, 68, 87, 2);
        players.add(player2);
      }

      //generate random position for the bubble to start dropping
      Random rand = new Random();
      int bubbleStartX;
      if (!GameLanding.getIs2P()) {
        //if 1p
        bubbleStartX = 200;
      } else {
        // if 2p
        bubbleStartX = 300;
      }
      int bubbleStartY = rand.nextInt(100) + 100;

      Bubble bubble = new Bubble(
          new PVector(bubbleStartX, bubbleStartY),
          new PVector(1, 1),
          bubbleStartSize,
          bubbleStartSpeed,
          new Color(0, 0, 255), window,
          new PVector(2, 5)
      );
      bubbles.add(bubble);

    }
    sprites.addAll(bubbles);
    sprites.addAll(players);
  }

  public static void levelUpdate(GameWindow window) {
    switch (Scene.levelCount) {
      case 0 -> {
        for (Player player : players) {
          player.position.x = GameWindow.getX() - player.size.x * 2;
        }
        databaseHelper.loadLevel(bubbles, sprites, window, "level1");
      }
      case 1 -> databaseHelper.loadLevel(bubbles, sprites, window, "level2");
      case 2 -> {
        Scene.sounds.playSfx(Scene.sounds.winAudio);
        Scene.scoreBar.finishedLevel((int) Scene.timer.getRemaining() / 10000);
        Scene.scoreBar.addScore(Scene.lives.getLives() * 1000);
        if (databaseHelper != null) {
          databaseHelper.put("score", Scene.scoreBar.getValue());
        }
        Scene.levelCount = 0;
        GameWindow.screen = Screen.win;
      }
      default -> System.err.println("Level count is not valid");
    }
    Scene.levelCount++;
    Scene.timer.resetTimer(window);
  }

  /** Callled to reset the game.
   *
   * @param window game window
   */
  public static void gameReset(GameWindow window) {
    Scene.levelCount = 0;
    Lives lives = Lives.getInstance();
    lives.setLives(3);
    ScoreBar.getInstance().resetValue();
    Timer.getInstance(window).resetTimer(window);
    players.clear();
    sprites.clear();
    bubbles.clear();
    removedSprites.clear();
    Scene.isPaused = false;
  }
}


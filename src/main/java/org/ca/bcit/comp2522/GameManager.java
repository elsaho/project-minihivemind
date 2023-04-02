package org.ca.bcit.comp2522;

import processing.core.PVector;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
  public static ArrayList<Player> players = new ArrayList<>();
  public static PVector playerSize = new PVector(42, 64);
  public static int playerSpeed = 5;

  /**
   * Bubble constants.
   */
  public static PVector bubbleStartSize = new PVector(100, 100);
  public static int bubbleStartSpeed = 5;

  /**
   * Gameplay constants.
   */
  private Lives lives;

  private Timer timer;

  /**
   * Gameplay.
   */
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
  public static ShootLine shootLine;
  public static Player player;

  public static Player player2;
  public static ArrayList<Sprite> sprites = new ArrayList<>();
  public static ArrayList<Bubble> bubbles = new ArrayList<>();

  public static ArrayList<Sprite> removedSprites = new ArrayList<>();


  public static void clear() {
    players.clear();
    sprites.clear();
    bubbles.clear();
    removedSprites.clear();
  }
  public static void level1 (GameWindow window) throws LineUnavailableException, FileNotFoundException {
    player = new Player(
        new PVector(GameWindow.getX() / 2 + 50, GameWindow.getY() - playerSize.y),
        new PVector(0, 1), playerSize, playerSpeed,
        new Color(0, 255, 255), window, 37, 39, 38, 1);
    players.add(player);

    if (SelectMultiPlayer.getIs2P()) {
      player2 = new Player(
          new PVector(GameWindow.getX() / 2 - 175, GameWindow.getY() - playerSize.y),
          new PVector(0, 1), new PVector(56, 69), playerSpeed,
          new Color(0, 255, 255), window, 65, 68, 87, 2);
      players.add(player2);
    }

    //generate random position for the bubble to start dropping
    Random rand = new Random();
    int bubbleStartX = 700;
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
    sprites.addAll(players);

  }

}


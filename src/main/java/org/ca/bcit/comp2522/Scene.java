package org.ca.bcit.comp2522;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import processing.core.PImage;

/**
 * Scene class. The class that contains all the sprites in the game.
 * This class is responsible for updating and displaying all the sprites.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class Scene {

  // Fields
  private static final ArrayList<Bubble> bubbles = GameManager.bubbles;
  private static final ArrayList<Player> players = GameManager.players;
  private static final ArrayList<Sprite> sprites = GameManager.sprites;
  private final ArrayList<Sprite> removedSprites = GameManager.removedSprites;
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  private final Lives lives;
  private final ScoreBar scoreBar;

  public static int levelCount = 0;

  private Timer timer;
  private PImage bg;

  // Static fields
  public static boolean isPaused = false;
  public static boolean isImmune = false;
  public static long lastCollisionTime = 0;

  public static SoundEffects sounds;
  public static Button pause;

  /**
   * Constructor for objects of class Scene.
   */
  public Scene()  {

    try {
      sounds = new SoundEffects();
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }

    this.scoreBar = ScoreBar.getInstance();
    this.lives = Lives.getInstance();
  }

  /**
   * Loads up the game and resets everything.
   */
  public void setup(GameWindow window) throws LineUnavailableException, FileNotFoundException {

    PImage pauseImage = window.loadImage("../assets/pauseBtn.png");
    pause = new Button(760, 30, 30, 30, pauseImage);

    GameManager.level0(window);
    /* If you want to change the image,
     * you must make the image the exact size of the window (800 x 600)
     */
    bg = window.loadImage("../assets/SkyBackground.png");

    //starts the timer
    this.timer = Timer.getInstance(window);
  }

  /**
   * Displays the game.
   *
   * @param window as a GameWindow
   */
  public void display(GameWindow window) {
    window.background(bg);

    pause.display(window);

    for (Sprite sprite : sprites) {
      sprite.display(window);
    }
    // Displays the scoreBar
    scoreBar.display(window);
  }

  /**
   * Updates the game.
   *
   * @param window as a GameWindow
   */
  public void update(GameWindow window) throws LineUnavailableException, FileNotFoundException {
    //check if the game is paused
    if (!(databaseHelper == null)) {
      GameManager.pause(window);
    }

    for (Player player : players) {
      player.update();
    }
    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Bubble bubble : bubbles) {
      bubble.bounce();
      bubble.display(window);

      for (Player player : players) {
        if (bubble.collided(player) && !isImmune) {
          bubble.update(bubble);
        }

        // Check if the player's immunity has expired
        if (isImmune && System.currentTimeMillis() - lastCollisionTime > 1500) {
          isImmune = false;
        }

        if (player.getPlayersLine() != null && bubble.collided(player.getPlayersLine())) {
          sounds.playSfx(sounds.popAudio);
          player.setPlayersLine(null);
          bubblesToRemove.add(bubble);
          if (bubble.size.x > Bubble.MIN_SIZE) {
            newBubbles.addAll(bubble.spawnBubbles());
          }
          //update score
          scoreBar.update(bubble, true, false);

          if (databaseHelper != null) {
            //save score to database everytime bubble is popped
            databaseHelper.put("score", scoreBar.getValue());
          }
        }
      }
    }

    if (timer.getRemaining() <= 0 || lives.getLives() <= 0) {
      GameWindow.screen = Screen.lose;
      if (databaseHelper != null) {
        databaseHelper.put("score", scoreBar.getValue());
      }
    }

    //Remove bubbles that have been popped, and add new bubbles
    bubbles.removeAll(bubblesToRemove);
    bubbles.addAll(newBubbles);
    removedSprites.addAll(bubblesToRemove);
    for (Sprite sprite : removedSprites) {
      sprites.remove(sprite);
    }

    //Game victory
    if (bubbles.isEmpty()) {
      switch (levelCount) {
        case 0:
          for (Player player : players) {
            player.position.x = GameWindow.getX() - player.size.x * 2;
          }
          databaseHelper.loadLevel(bubbles, window, "level1");
          break;
        case 1:
          databaseHelper.loadLevel(bubbles, window, "level2");
          break;
        case 2:
          sounds.playSfx(sounds.winAudio);
          scoreBar.finishedLevel((int) timer.getRemaining() / 10000);
          scoreBar.addScore(lives.getLives() * 1000);
          if (databaseHelper != null) {
            databaseHelper.put("score", scoreBar.getValue());
          }
          levelCount = 0;
          GameWindow.screen = Screen.win;
          break;
        default:
          System.err.println("Level count is not valid");
          break;
      }
      levelCount++;
      timer.resetTimer(window);
    }

  }

  /** Methods for testing purposes. */

  public SoundEffects getSounds() {
    return sounds;
  }

  public Player getPlayer() {
    return GameManager.player;
  }

  public ArrayList<Sprite> getSprites() {
    return sprites;
  }

  public ArrayList<Sprite> getRemovedSprites() {
    return removedSprites;
  }

  public Lives getLives() {
    return lives;
  }

  public Timer getTimer() {
    return timer;
  }

  public ScoreBar getScoreBar() {
    return scoreBar;
  }

  public PImage getBg() {
    return bg;
  }

  public ArrayList<Bubble> getBubbles() {
    return bubbles;
  }

  public static Button getPause() {
    return pause;
  }


}
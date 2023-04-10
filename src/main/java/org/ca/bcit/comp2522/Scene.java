package org.ca.bcit.comp2522;

import processing.core.PImage;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Scene class. The class that contains all the sprites in the game.
 * This class is responsible for updating and displaying all the sprites.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */

public class Scene implements Displayable {

  // Fields
  private static final ArrayList<Bubble> bubbles = GameManager.bubbles;
  private static final ArrayList<Player> players = GameManager.players;
  private static final ArrayList<Sprite> sprites = GameManager.sprites;
  private final ArrayList<Sprite> removedSprites = GameManager.removedSprites;
  private final DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

  public static  Lives lives;
  public static ScoreBar scoreBar;

  public static int levelCount = 0;

  public static Timer timer;
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

    scoreBar = ScoreBar.getInstance();
    lives = Lives.getInstance();
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
    timer = Timer.getInstance(window);
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

    ArrayList<Bubble> newBubbles = new ArrayList<>();
    ArrayList<Bubble> bubblesToRemove = new ArrayList<>();

    for (Player player : players) {
      player.update();
    }

    for (Bubble bubble : bubbles) {
      bubble.update();

      for (Player player : players) {
        if (bubble.collided(player) && !isImmune) {
          bubble.scoreUpdate(bubble); //update score
          if (lives.getLives() == 0) {
            Scene.sounds.playLoseAudio();
          }
        }

        // Check if the player's immunity has expired
        if (isImmune && System.currentTimeMillis() - lastCollisionTime > 1500) {
          isImmune = false;
        }
        // checks if players only line exists and if it has collided with a bubble
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
    sprites.addAll(newBubbles);
    removedSprites.addAll(bubblesToRemove);
    for (Sprite sprite : removedSprites) {
      sprites.remove(sprite);
    }

    //Game victory
    if (bubbles.isEmpty()) {
      Scene.sounds.playSfx(Scene.sounds.winAudio);
      GameManager.levelUpdate(window);
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
}
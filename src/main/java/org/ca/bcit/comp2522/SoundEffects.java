package org.ca.bcit.comp2522;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class for handling background music.
 *
 * @author Haurence Li, Elsa Ho, Mai Vu, Tomek Stojek, and Troy Calaquian
 */
public class SoundEffects {

  //Audio clips
  protected final Clip bgm;
  protected final Clip popAudio;
  protected final Clip oofAudio;
  protected final Clip shootAudio;
  protected final Clip loseAudio;
  protected final Clip winAudio;
  public static boolean isBGMPlaying = false;


  /**
   * Constructor for the SouneEffects class.
   *
   * @throws FileNotFoundException e
   * @throws LineUnavailableException e
   */
  public SoundEffects() throws FileNotFoundException, LineUnavailableException {
    // Load the background music
    Path bgmPath = Paths.get("assets", "Sound", "sound.wav");
    bgm = loadAudio(bgmPath);
    // Load the pop sound effect
    Path popPath = Paths.get("assets", "Sound", "pop.wav");
    popAudio = loadAudio(popPath);
    //Load lose life sound effect
    Path oofPath = Paths.get("assets", "Sound", "oof.wav");
    oofAudio = loadAudio(oofPath);
    //Load the shooting sound effect
    Path shootPath = Paths.get("assets", "Sound", "shoot.wav");
    shootAudio = loadAudio(shootPath);
    //Load lose game sound effect
    Path losePath = Paths.get("assets", "Sound", "loseGame.wav");
    loseAudio = loadAudio(losePath);
    //Load win game sound effect
    Path winPath = Paths.get("assets", "Sound", "winGame.wav");
    winAudio = loadAudio(winPath);
  }


  /**
   * Starts playing the background music.
   */
  public void playBgm() {
    if (!isBGMPlaying) {
      bgm.loop(Clip.LOOP_CONTINUOUSLY);
      isBGMPlaying = true;
    }
  }

  /**
   * Plays pop sound effect.
   */
  public void playPop() {
    popAudio.setFramePosition(0);
    popAudio.start();
  }

  /**
   * Plays oof sound effect.
   */
  public void playOof() {
    oofAudio.setFramePosition(0);
    oofAudio.start();
  }

  /**
   * Plays shooting sound effect.
   */
  public void playShoot() {
    shootAudio.setFramePosition(0);
    shootAudio.start();
  }

  /**
   * Plays oof sound effect.
   */
  public void playLoseAudio() {
    loseAudio.setFramePosition(0);
    loseAudio.start();
  }

  /**
   * Plays oof sound effect.
   */
  public void playWinAudio() {
    winAudio.setFramePosition(0);
    winAudio.start();
  }

  /**
   * Stops playing the background music.
   */
  public void stopBgm() {
    bgm.stop();
  }

  Clip loadAudio(Path path) throws  LineUnavailableException {
    try {
      AudioInputStream audioInputStream =
          AudioSystem.getAudioInputStream(new File(path.toFile().toURI()));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      return clip;
    } catch (UnsupportedAudioFileException | IOException e) {
      throw new RuntimeException(e);
    }
  }

}

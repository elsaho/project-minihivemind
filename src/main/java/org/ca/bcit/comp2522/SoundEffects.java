package org.ca.bcit.comp2522;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Class for handling background music.
 * @author Haurence Li, Elsa Ho, Mai Vu, Tomek Stojek, and Troy Calaquian
 */
public class SoundEffects {

  //Audio clips
  private final Clip bgm;
  private final Clip popAudio;
  private final Clip oofAudio;
  private final Clip loseAudio;

  /**
   * Constructor for the SouneEffects class
   * @throws FileNotFoundException e
   * @throws LineUnavailableException
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
    //Load lose game sound effect
    Path losePath = Paths.get("assets", "Sound", "loseGame.wav");
    loseAudio = loadAudio(losePath);
  }


  /**
   * Starts playing the background music
   */
  public void playBGM(){
    bgm.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /**
   * Plays pop sound effect
   */
  public void playPop(){
    popAudio.setFramePosition(0);
    popAudio.start();
  }

  /**
   * Plays oof sound effect
   */
  public void playOof(){
    oofAudio.setFramePosition(0);
    oofAudio.start();
  }

  /**
   * Plays oof sound effect
   */
  public void playLoseAudio(){
    loseAudio.setFramePosition(0);
    loseAudio.start();
  }

  /**
   * Stops playing the background music
   */
  public void stopBGM(){
    bgm.stop();
  }

  /**
   * Returns true if background music is playing
   * @return boolean
   */
  public boolean isPlaying() {
    return bgm.isActive();
  }

  private Clip loadAudio(Path path) throws FileNotFoundException, LineUnavailableException {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path.toFile().toURI()));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      return clip;
    } catch (UnsupportedAudioFileException | IOException e) {
      throw new RuntimeException(e);
    }
  }

}

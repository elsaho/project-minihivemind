package org.ca.bcit.comp2522;

import javax.sound.sampled.*;
import java.io.File;
import java. io. FileNotFoundException;
import java.io.IOException;

/**
 * Class for handling background music.
 * @author Haurence Li, Elsa Ho, Mai Vu, Tomek Stojek, and Troy Calaquian
 */
public class SoundEffects {

  //Audio clips
  private Clip audio;
  private Clip popAudio;

  /**
   * Constructor for the SouneEffects class
   * @throws FileNotFoundException
   * @throws LineUnavailableException
   */
  public SoundEffects() throws FileNotFoundException, LineUnavailableException {
    try {
      // Load the background music
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/Sound/sound.wav"));
      audio = AudioSystem.getClip();
      audio.open(audioInputStream);

      // Load the pop sound effect
      audioInputStream = AudioSystem.getAudioInputStream(new File("assets/Sound/pop.wav"));
      popAudio = AudioSystem.getClip();
      popAudio.open(audioInputStream);
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw e;
    } catch (UnsupportedAudioFileException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Starts playing the background music
   */
  public void playBGM(){
    audio.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /**
   * Plays pop sound effect
   */
  public void playPop(){
    popAudio.setFramePosition(0);
    popAudio.start();
  }

  /**
   * Stops playing the background music
   */
  public void stopBGM(){
    audio.stop();
    audio.close();
  }

  /**
   * Returns true if background music is playing
   * @return boolean
   */
  public boolean isPlaying() {
    return audio.isActive();
  }

}

package org.ca.bcit.comp2522;

import javax.sound.sampled.*;
import java.io.File;
import java. io. FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for handling background music.
 * @author Haurence Li
 */
public class SoundEffects {
  private Clip audio;

  public SoundEffects() throws FileNotFoundException, LineUnavailableException {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/Sound/bgm.wav"));
      audio = AudioSystem.getClip();
      audio.open(audioInputStream);
    } catch (FileNotFoundException | LineUnavailableException e) {
      throw e;
    } catch (UnsupportedAudioFileException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void playBGM(){
    audio.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stopBGM(){
    audio.stop();
    audio.close();
  }


}

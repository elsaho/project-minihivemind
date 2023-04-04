package org.ca.bcit.comp2522;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SoundEffects
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
class SoundEffectsTest {
  private SoundEffects testSound;

  @BeforeEach
  void setUp() throws LineUnavailableException, FileNotFoundException {
    testSound = new SoundEffects();
  }

  @Test
  void playBGMTest() throws InterruptedException {
    testSound.playBGM();
    Thread.sleep(100);
    assertTrue(testSound.bgm.isActive());
  }

  @Test
  void playPopTest() throws InterruptedException {
    testSound.playPop();
    Thread.sleep(100);
    assertTrue(testSound.popAudio.isActive());
  }

  @Test
  void playOofTest() throws InterruptedException {
    testSound.playOof();
    Thread.sleep(100);
    assertTrue(testSound.oofAudio.isActive());
  }

  @Test
  void playLoseAudioTest() throws InterruptedException {
    testSound.playLoseAudio();
    Thread.sleep(100);
    assertTrue(testSound.loseAudio.isActive());
  }

  @Test
  void playWinAudioTest() throws InterruptedException {
    testSound.playWinAudio();
    Thread.sleep(100);
    assertTrue(testSound.winAudio.isActive());
  }

  @Test
  void stopBGMTest() throws InterruptedException {
    testSound.playBGM();
    Thread.sleep(100);
    Assertions.assertTrue(testSound.bgm.isActive());

    testSound.stopBGM();
    Thread.sleep(100);
    Assertions.assertFalse(testSound.bgm.isActive());
  }

  @Test
  void runTimeExceptionCatchTest(){
    Assertions.assertThrows(RuntimeException.class, () -> {
      Path invalidPath = Paths.get("invalid", "path");
      testSound.loadAudio(invalidPath);
    });
  }
}
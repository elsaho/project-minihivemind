package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {

  GameWindow gameWindow;

  Scene sceneTest;

  @BeforeEach
  public void setUp() {
    gameWindow = new GameWindow();
    // Set up the PApplet instance
    PApplet.runSketch(new String[]{"GameWindowTest"}, gameWindow);
    gameWindow.setup();
    sceneTest = gameWindow.getScene();
  }

  @Test
  public void testExistence() {
    assertNotNull(sceneTest);
  }

  @Test
  public void testSceneConstructor() {
    assertNotNull(sceneTest.getSounds());
    assertNotNull(sceneTest.getLives());
    assertNotNull(sceneTest.getScoreBar());
  }

  @Test
  public void testSetup() throws LineUnavailableException, FileNotFoundException {
    sceneTest.setup(gameWindow);
    assertNotNull(sceneTest.getBg());
    assertNotNull(sceneTest.getPause());
    assertNotNull(sceneTest.getTimer());
  }

  @Test
  public void testDisplay() {
    sceneTest.display(gameWindow);
    assertNotNull(sceneTest.getSprites());
  }

}

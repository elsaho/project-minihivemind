package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

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
  }

  @Test
  public void testExistence() {
    gameWindow.setup();
    sceneTest = gameWindow.getScene();
    assertNotNull(sceneTest);
  }

  @Test
  public void testSceneConstructor() {
    gameWindow.setup();
    sceneTest = gameWindow.getScene();
    assertNotNull(sceneTest.getInputHandler());
    assertNotNull(sceneTest.getSounds());
    assertNull(Scene.getShootLine());
    assertNotNull(sceneTest.getPlayer());
    assertNotNull(sceneTest.getSprites());
    assertNotNull(sceneTest.getRemovedSprites());
    assertNotNull(sceneTest.getLives());
    assertNotNull(sceneTest.getTimer());
    assertNotNull(sceneTest.getScoreBar());
  }

  @Test
  public void testSetup() {
    gameWindow.setup();
    sceneTest = gameWindow.getScene();
    sceneTest.setup(gameWindow);
    assertNotNull(sceneTest.getBg());
    ArrayList<Sprite> sprites = sceneTest.getSprites();
    assertEquals(sceneTest.getPlayer(), sprites.get(0));
    ArrayList<Bubble> bubbles = sceneTest.getBubbles();
    assertNotNull(bubbles.get(0));
    assertEquals(1, bubbles.size());
  }

}

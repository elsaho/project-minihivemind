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
    gameWindow.setup();
    sceneTest = gameWindow.getScene();
  }

  @Test
  public void testExistence() {
    assertNotNull(sceneTest);
  }

  @Test
  public void testSceneConstructor() {
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
    sceneTest.setup(gameWindow);
    assertNotNull(sceneTest.getBg());
    ArrayList<Sprite> sprites = sceneTest.getSprites();
    assertEquals(sceneTest.getPlayer(), sprites.get(0));
    assertEquals(2, sprites.size());
    ArrayList<Bubble> bubbles = sceneTest.getBubbles();
    assertNotNull(bubbles.get(0));
    assertEquals(1, bubbles.size());
  }

  @Test
  public void testUpdateLineInstance() {

  }

}

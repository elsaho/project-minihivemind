package org.ca.bcit.comp2522;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;

/**
 * Abstract class for all Game Screens.
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public abstract class GameScreen implements Displayable {

  public void display(GameWindow window) {
  }

  public void screenUpdate(GameWindow window) throws LineUnavailableException, FileNotFoundException {
  }

  }

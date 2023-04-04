package org.ca.bcit.comp2522;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonTest {

  Button buttonTest;
  @BeforeEach
  public void setUp() {
    buttonTest = new Button(0, 0, 0, 0, null);
  }

  @Test
  public void testIsClicked() {
    assertTrue(buttonTest.isClicked(0, 0, true));
    assertFalse(buttonTest.isClicked(200, 200, false));
  }
}

package org.ca.bcit.comp2522;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriteBubble {

  public static void main(String[] args) {

    JSONObject bubbleTroubleJson = new JSONObject();
    JSONArray levels = new JSONArray();

    // Level 1
    JSONObject level1 = new JSONObject();
    level1.put("id", 1);
    level1.put("name", "Level 1");
    level1.put("background", "level1.png");

    JSONArray ballsLevel1 = new JSONArray();

    JSONObject ball1Level1 = new JSONObject();
    ball1Level1.put("id", 1);
    ball1Level1.put("position.x", 400);
    ball1Level1.put("position.y", 100);
    ball1Level1.put("size", 100);
    ballsLevel1.add(ball1Level1);


    level1.put("balls", ballsLevel1);

    JSONObject playerLevel1 = new JSONObject();


    level1.put("player", playerLevel1);


    bubbleTroubleJson.put("levels", levels);

    // Writing the JSON to file
    try (FileWriter file = new FileWriter("bubbleTrouble.json")) {
      file.write(bubbleTroubleJson.toJSONString());
      System.out.println("Successfully created bubbleTrouble.json file");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

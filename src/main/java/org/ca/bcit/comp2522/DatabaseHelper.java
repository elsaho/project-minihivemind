package org.ca.bcit.comp2522;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.Date;

/**
 * A helper class for connecting to MongoDB.
 * This class is implemented as a singleton.
 * To get the singleton instance, use DatabaseHelper.getInstance().
 * Example usage: DatabaseHelper.getInstance(uri).put(key, value);
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class DatabaseHelper {

  private static final String DATABASE_NAME = "BubbleTrouble";
  private static DatabaseHelper instance = null;

  private final MongoClient mongoClient;
  private final MongoDatabase database;

  /**
   * Private constructor to prevent outside instantiation.
   *
   * @param uri the uri of the database
   */
  private DatabaseHelper(String uri) {
    ConnectionString connectionString = new ConnectionString(uri);
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase(DATABASE_NAME);
  }

  /**
   * Returns the singleton instance of DatabaseHelper.
   *
   * @return the singleton instance of DatabaseHelper
   */
  public static DatabaseHelper getInstance() {
    if (instance == null) {
      try {
        instance = new DatabaseHelper("mongodb+srv://Bubble:comp2522@2522.w2dd0ev.mongodb.net/?retryWrites=true&w=majority");
      } catch (Exception e) {
        System.out.println("Unable to connect to MongoDB: " + e.getMessage());
      }
    }
    return instance;
  }


  /**
   * Puts a key-value pair into the database.
   *
   * @param key the key
   * @param value the value
   */
  public <T> void put(String key, T value) {
    if (instance != null) {
      Document document = new Document(key, value)
          .append("timestamp", new Date());
      new Thread(() -> database.getCollection("scores").insertOne(document)).start();
    }
  }


  /** Get the highest score from the database
   *
   * @return int
   */
  public <T> T getHighestScore(String collectionName, String fieldName, Class<T> clazz) {
    if (instance != null) {
      MongoCollection<Document> scoresCollection = database.getCollection(collectionName);
      Document highestScoreDocument = scoresCollection.find().sort(new Document(fieldName, -1)).limit(1).first();
      return highestScoreDocument.get(fieldName, clazz);
    }
    return null;
  }


  public void close() {
    mongoClient.close();
  }

  public void saveGame(GameWindow window) {
    if (instance != null) {
      Document document = new Document();

      // Save the game timer
      document.append("timer", Timer.getInstance(window).getRemaining());

      // Save the number of lives
      document.append("lives", Lives.getInstance().getLives());

      // Save the score bar
      document.append("scoreBar", ScoreBar.getInstance().getValue());

      // Save the last collision time
      document.append("lastCollisionTime", Scene.lastCollisionTime);

      // Save the immune status
      document.append("isImmune", Scene.isImmune);

      // Save the player
      ArrayList<Document> playerDocuments = new ArrayList<>();
      for (Player player: GameManager.players) {
        Document playerDoc = new Document()
            .append("position.x", player.getPosition().x)
            .append("position.y", player.getPosition().y)
            .append("direction.x", player.getDirection().x)
            .append("direction.y", player.getDirection().y)
            .append("size.x", player.getSize().x)
            .append("size.y", player.getSize().y)
            .append("speed", player.speed)
            .append("left", player.getLeft())
            .append("right", player.getRight())
            .append("up", player.getUp())
            .append("player", player.getPlayerNo());
        playerDocuments.add(playerDoc);
      }
      document.append("players", playerDocuments);

      // Save the bubbles
      ArrayList<Document> bubbleDocuments = new ArrayList<>();
      for (Bubble bubble: GameManager.bubbles) {
        Document bubbleDoc = new Document()
            .append("position.x", bubble.getPosition().x)
            .append("position.y", bubble.getPosition().y)
            .append("direction.x", bubble.getDirection().x)
            .append("direction.y", bubble.getDirection().y)
            .append("size.x", bubble.getSize().x)
            .append("size.y", bubble.getSize().y)
            .append("speed", bubble.speed)
            .append("velocity.x", bubble.velocity.x)
            .append("velocity.y", bubble.velocity.y);
        bubbleDocuments.add(bubbleDoc);
      }
      document.append("bubbles", bubbleDocuments);

      document.append("timestamp", new Date());
      new Thread(() -> database.getCollection("state").insertOne(document)).start();
    }
  }

  public void loadGame(ArrayList<Player> players, ArrayList<Bubble> bubbles) {
    if (instance != null) {
      // Query the collection for the latest saved game state
      Document savedGameState = database.getCollection("state").find()
          .sort(new Document("timestamp", -1))
          .first();

      if (savedGameState != null) {

        // Load the number of lives
        Lives.getInstance().setLives(savedGameState.getInteger("lives"));

        // Load the last collision time
        Scene.lastCollisionTime = savedGameState.getLong("lastCollisionTime");

        // Load the immune status
        Scene.isImmune = savedGameState.getBoolean("isImmune");

        // Load the players
        ArrayList<Document> playerDocuments = savedGameState.get("players", ArrayList.class);
        for (int i = playerDocuments.size() - 1; i >=0; i--) {
          Document playerDoc = playerDocuments.get(i);
          Player player = players.get(i);
          player.position.x = playerDoc.getDouble("position.x").floatValue();
          player.position.y = playerDoc.getDouble("position.y").floatValue();
          player.direction.x = playerDoc.getDouble("direction.x").floatValue();
          player.direction.y = playerDoc.getDouble("direction.y").floatValue();
          player.speed = playerDoc.getDouble("speed").floatValue();
        }

        // Load the bubbles
        ArrayList<Document> bubbleDocuments = savedGameState.get("bubbles", ArrayList.class);
        for (int i = 0; i < bubbleDocuments.size(); i++) {
          Document bubbleDoc = bubbleDocuments.get(i);
          Bubble bubble = bubbles.get(i);
          bubble.position.x = bubbleDoc.getDouble("position.x").floatValue();
          bubble.position.y = bubbleDoc.getDouble("position.y").floatValue();
          bubble.direction.x = bubbleDoc.getDouble("direction.x").floatValue();
          bubble.direction.y = bubbleDoc.getDouble("direction.y").floatValue();
          bubble.size.x = bubbleDoc.getDouble("size.x").floatValue();
          bubble.speed = bubbleDoc.getDouble("speed").floatValue();
          bubble.velocity = new PVector(bubbleDoc.getDouble("velocity.x").floatValue(),
              bubbleDoc.getDouble("velocity.y").floatValue());
        }
      }
    }
  }
}


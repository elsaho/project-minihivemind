package org.ca.bcit.comp2522;

import static org.ca.bcit.comp2522.GameManager.bubbleStartSize;
import static org.ca.bcit.comp2522.GameManager.bubbleStartSpeed;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import org.bson.Document;
import processing.core.PVector;

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

  public final MongoClient mongoClient;
  private final MongoDatabase database;

  public static PVector playerSize = new PVector(42, 64);
  public static int playerSpeed = 5;

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
        instance = new DatabaseHelper("mongodb+srv://Bubble:comp2522@2522"
            + ".w2dd0ev.mongodb.net/?retryWrites=true&w=majority");
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
    String colName;
    if (GameLanding.getIs2P()) {
      colName = "score2P";
    } else {
      colName = "score1P";
    }
    if (instance != null) {
      Document document = new Document(key, value)
          .append("timestamp", new Date());
      new Thread(() -> database.getCollection(colName).insertOne(document)).start();
    }
  }


  /** Get the highest score from the database.
   *
   * @return int highest score
   */
  public <T> T getHighestScore(String collectionName, String fieldName, Class<T> clazz) {
    if (instance != null) {
      //get Collection
      MongoCollection<Document> scoresCollection = database.getCollection(collectionName);
      //get highest score
      Document highestScoreDocument = scoresCollection.find()
          .sort(new Document(fieldName, -1)).limit(1).first();
      if (highestScoreDocument != null) {
        return highestScoreDocument.get(fieldName, clazz);
      }
    }
    return null;
  }

  /** Save current game data to the database.
   *
   * @param window GameWindow
   */
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
      for (Player player : GameManager.players) {
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
      for (Bubble bubble : GameManager.bubbles) {
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

  /** Load levels to datatbase.
   *
   */
  public void uploadLvls(GameWindow window) throws LineUnavailableException, FileNotFoundException {
    ArrayList<Player> players = new ArrayList<>();
    Player playerSolo = new Player(
        new PVector((float) GameWindow.getX() / 2 - 50, GameWindow.getY() - playerSize.y),
        new PVector(0, 1), playerSize, playerSpeed,
        new Color(0, 255, 255), window, 37, 39, 38, 1);
    players.add(playerSolo);
    Player player1 = new Player(
          new PVector((float) GameWindow.getX() / 2 + 50, GameWindow.getY() - playerSize.y),
          new PVector(0, 1), playerSize, playerSpeed,
          new Color(0, 255, 255), window, 37, 39, 38, 1);
    players.add(player1);
    Player player2 = new Player(
          new PVector((float) GameWindow.getX() / 2 - 175, GameWindow.getY() - playerSize.y),
          new PVector(0, 1), new PVector(56, 69), playerSpeed,
          new Color(0, 255, 255), window, 65, 68, 87, 2);
    players.add(player2);

    // Save the player
    Document document = new Document();
    ArrayList<Document> playerDocuments = new ArrayList<>();
    for (Player player : players) {
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

    ArrayList<Bubble> bubbles = new ArrayList<>();
    Random rand = new Random();
    int bubbleStartY = rand.nextInt(100) + 100;

    Bubble bubble1 = new Bubble(
        new PVector(507, bubbleStartY),
        new PVector(1, 1),
        bubbleStartSize,
        bubbleStartSpeed,
        new Color(0, 0, 255), window,
        new PVector(2, 5)
    );
    bubbles.add(bubble1);

    Bubble bubble2 = new Bubble(
        new PVector(169, bubbleStartY),
        new PVector(1, 1),
        bubbleStartSize,
        bubbleStartSpeed,
        new Color(0, 0, 255), window,
        new PVector(2, 5)
    );
    bubbles.add(bubble2);

    // Save the bubbles
    ArrayList<Document> bubbleDocuments = new ArrayList<>();
    for (Bubble bubble : bubbles) {
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

    new Thread(() -> database.getCollection("level2").insertOne(document)).start();
  }

  /**Load the game state from the database.
   *
   * @param players current players
   * @param bubbles current bubbles
   */
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
        List<Document> playerDocuments = savedGameState.getList("players", Document.class);
        for (int i = playerDocuments.size() - 1; i >= 0; i--) {
          System.out.println("Loading player " + i);
          Document playerDoc = playerDocuments.get(i);
          Player player = players.get(i);
          player.position.x = playerDoc.getDouble("position.x").floatValue();
          player.position.y = playerDoc.getDouble("position.y").floatValue();
          player.direction.x = playerDoc.getDouble("direction.x").floatValue();
          player.direction.y = playerDoc.getDouble("direction.y").floatValue();
          player.speed = playerDoc.getDouble("speed").floatValue();
        }

        // Load the bubbles
        List<Document> bubbleDocuments = savedGameState.getList("bubbles", Document.class);
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

  /**Pull the game level from the database.
   *
   * @param bubbles current bubbles
   */
  public void loadLevel(ArrayList<Bubble> bubbles, GameWindow window) {

    Document savedGameState = database.getCollection("level2").find().first();

    // Load the bubbles
    List<Document> bubbleDocuments = savedGameState.getList("bubbles", Document.class);
    for (int i = 0; i < bubbleDocuments.size(); i++) {
      Document bubbleDoc = bubbleDocuments.get(i);
      Bubble bubble = new Bubble(
          new PVector(bubbleDoc.getDouble("position.x").floatValue(),
              bubbleDoc.getDouble("position.y").floatValue()),
          new PVector(bubbleDoc.getDouble("direction.x").floatValue(),
              bubbleDoc.getDouble("direction.y").floatValue()),
          new PVector(bubbleDoc.getDouble("size.x").floatValue(),
              bubbleDoc.getDouble("size.y").floatValue()),
          bubbleDoc.getDouble("speed").floatValue(), new Color(255, 255, 255), window,
          new PVector(bubbleDoc.getDouble("velocity.x").floatValue(),
              bubbleDoc.getDouble("velocity.y").floatValue()));
      bubbles.add(bubble);
    }
  }
}

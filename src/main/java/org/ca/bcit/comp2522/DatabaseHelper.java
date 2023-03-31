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

import java.util.ArrayList;
import java.util.Date;

/**
 * A helper class for connecting to MongoDB.
 *
 * This class is implemented as a singleton.
 * To get the singleton instance, use DatabaseHelper.getInstance().
 * Example usage: DatabaseHelper.getInstance(uri).put(key, value);
 *
 * @author Mai Vu, Elsa Ho, Tomasz Stojek, Haurence Li, Troy Calaquian
 * @version 2023
 */
public class DatabaseHelper {
  private static final String DATABASE_NAME = "BubbleTrouble";
  private static final String COLLECTION_NAME = "level1";
  private static DatabaseHelper instance = null;

  private MongoClient mongoClient;
  private MongoDatabase database;
  private MongoCollection<Document> collection;

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
  public void put(String key, int value) {
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
  public int getHighestScore() {
    if (instance != null) {
      MongoCollection<Document> scoresCollection = database.getCollection("scores");
      Document highestScoreDocument = scoresCollection.find().sort(new Document("score", -1)).limit(1).first();
      int highestScore = (int) highestScoreDocument.getInteger("score");
      return highestScore;
    }
    return 0;
  }

  public int getPlayerScore() {
    if (instance != null) {
      // Get the scores collection
      MongoCollection<Document> scoresCollection = database.getCollection("scores");

      // Sort the scores by timestamp in descending order
      Document sortCriteria = new Document("timestamp", -1);
      ArrayList<Document> sortedScores = scoresCollection.find().sort(sortCriteria).limit(1).into(new ArrayList<>());

      //get the score
      Document mostRecentScore = sortedScores.get(0);
      int score = mostRecentScore.getInteger("score");
      return score;
    }
    return 0;
  }


  public void close() {
    mongoClient.close();
  }

}


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

public class DatabaseHelper {
  private static final String DATABASE_NAME = "BubbleTrouble";
  private static final String COLLECTION_NAME = "level1";
  private MongoClient mongoClient;
  private MongoDatabase database;
  private MongoCollection<Document> collection;

  public DatabaseHelper(String uri) {
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

  public void put(String key, Object value) {
    Document document = new Document(key, value);
    document.append(key, value);
    new Thread(() -> database.getCollection("level1").insertOne(document)).start();
  }

  public void close() {
    mongoClient.close();
  }
  public static void main(String[] args) {
    DatabaseHelper databaseHelper = new DatabaseHelper("mongodb+srv://Bubble:comp2522@2522.w2dd0ev.mongodb.net/?retryWrites=true&w=majority");
    String key = "test";
    Object value = "value";
    databaseHelper.put(key, value);
    databaseHelper.close();
  }
}

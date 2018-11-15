package mongo;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// Flyweight factory
public class DBCollectionFactory {
	
	private static MongoDatabase db;
	private static MongoClient mc;
	
	private static String hostName = "localhost";
	private static int hostPort = 27017;
	private static String dbName = "MVC";
	// MongoCollections are thread safe, so sharing them should be fine
	private static HashMap<String, MongoCollection<Document>> collections = new HashMap<String, MongoCollection<Document>>();
	
	public static void initializeFactory() {
		mc = new MongoClient(hostName, hostPort);
		db = mc.getDatabase(dbName);
	}
	
	public static DBCollection getCollection(String collectionName) {
		if (mc == null || db == null) {
			System.out.println("initializing the factory");
			initializeFactory();
		}
		
		// Search for the collection
		if (collections.containsKey(collectionName)) {
			MongoCollection<Document> coll = (MongoCollection<Document>) collections.get(collectionName);
			return new DBCollection(mc, db, coll);
		}
		
		// else generate a new collection
		MongoCollection<Document> coll = db.getCollection(collectionName);
		collections.put(collectionName, coll);
		
		return new DBCollection(mc, db, coll);
	}

}

package mongo;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Flyweight factory for DBCollections
 * Controls access to database 
 * ensures only one MongoDatabase instance exists
 * and only one MongoCollection instance exists per collection
 * @author eoin
 *
 */
public class DBCollectionFactory {
	
	private static MongoDatabase db;
	private static MongoClient mc;
	
	private static String hostName = "localhost";
	private static int hostPort = 27017;
	private static String dbName = "MVC";
	// MongoCollections are thread safe, so sharing them should be fine
	private static HashMap<String, MongoCollection<Document>> collections = new HashMap<String, MongoCollection<Document>>();
	
	/**
	 * Static method to initialize one global instance of this factory
	 */
	public static void initializeFactory() {
		mc = new MongoClient(hostName, hostPort);
		db = mc.getDatabase(dbName);
	}
	
	/**
	 * Static method to create a DBCollection object
	 * This will only create a new MongoCollection if that collection has not been accessed before
	 * If this collection has been accessed before, it will use an existing MongoCollection
	 * @param collectionName String name of the collection the DBCollection represents
	 * @return DBCollection for the requested collection
	 */
	public static DBCollection getCollection(String collectionName) {
		if (mc == null || db == null) {
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

package mongo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {
	private MongoClient mc;
	private MongoDatabase db;
	
	// Should probably refactor this to use singleton
	public DatabaseManager() {
		mc = new MongoClient("localhost", 27017);
		db = mc.getDatabase("MVC");
	}
	
	public MongoCollection<Document> getCollection(String collectionName) {
		return db.getCollection(collectionName);
	}
	
	public Document getDocumentById(String collectionName, String id) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		ObjectId oid = new ObjectId(id);
		
		Document doc = new Document("_id", oid);
		
		return collection.find(doc).first();
	}
	
	public Iterable<Document> getDocumentsByFilter(String collectionName, Document docFilter) {
		MongoCollection<Document> coll = db.getCollection(collectionName);
		return coll.find(docFilter);
	}
	
	public Document findDocumentByFilter(String collectionName, Document docFilter) {
		MongoCollection<Document> coll = db.getCollection(collectionName);
		return coll.find(docFilter).first();
	}
	
	public void writeToDatabase(String collectionName, String id, Document doc) {
		MongoCollection<Document> coll = db.getCollection(collectionName);
		
		if (id == "") {
			coll.insertOne(doc);
		} else {
			coll.findOneAndReplace(new Document("_id", new ObjectId(id)), doc);
		}
	}
	
	public static void main(String args[]) {
//		DatabaseManager db = new DatabaseManager();
//		Document filter = new Document("user", "user");
//		Iterable<Document> it = db.getDocumentsByFilter("Notes", filter);
//		
//		for (Document d: it) {
//			System.out.println(d);
//		}
		
		Document doc = new Document("foo", "bar");
		
		System.out.println(doc.toJson());
		
		doc.put("foo", "baz");
		
		System.out.println(doc.toJson());
		
		doc.put("a", "b");
		
		System.out.println(doc.toJson());
	}
	
}

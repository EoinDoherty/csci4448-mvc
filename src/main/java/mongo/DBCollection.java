package mongo;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// Wrapper for MongoCollection
// Generated by DBCollectionFactory
public class DBCollection {
	
	private MongoClient mc;
	private MongoDatabase db;
	private MongoCollection<Document> collection;
	private final String ID_KEY = "_id"; 
	
	public DBCollection(MongoClient mc, MongoDatabase db, MongoCollection<Document> collection) {
		this.mc = mc;
		this.db = db;
		this.collection = collection;
	}
	
	public Document getDocumentById(String id) {
		Document doc = new Document(ID_KEY, new ObjectId(id));
		
		return collection.find(doc).first();
	}
	
	public void deleteById(String id) {
		collection.deleteOne(new Document(ID_KEY, new ObjectId(id)));
	}
	
	public Iterable<Document> getAllDocuments() {
		return collection.find();
	}
	
	public Iterable<Document> getDocumentsByValue(String key, String value) {
		Document docFilter = new Document(key, value);
		
		return collection.find(docFilter);
	}
	
	public Document getDocumentByValue(String key, String value) {
		Document docFilter = new Document(key, value);
		
		return collection.find(docFilter).first();
	}
	
	public Iterable<Document> getDocumentsByFilter(Document docFilter) {
		return collection.find(docFilter);
	}
	
	public Document getDocumentByFilter(Document docFilter) {
		return collection.find(docFilter).first();
	}
	
	public void writeToDatabase(Document doc) {
		collection.insertOne(doc);
	}
	
	public void writeToDatabase(String id, Document doc) {
		if (id == "") {
			writeToDatabase(doc);
		} else {
			collection.findOneAndReplace(new Document(ID_KEY, new ObjectId(id)), doc);
		}
	}
	
	public Iterable<Document> searchCollectionContents(String query, String username) {
		Document searchDoc = new Document("user", username); 
		searchDoc.put("$text", new Document("$search", query));
		
		return collection.find(searchDoc);
	}

}

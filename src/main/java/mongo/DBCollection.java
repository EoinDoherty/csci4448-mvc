package mongo;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Wrapper for MongoCollection
 * Generated by DBCollectionFactory
 * @author eoin
 *
 */
public class DBCollection {
	
	private MongoClient mc;
	private MongoDatabase db;
	private MongoCollection<Document> collection;
	private final String ID_KEY = "_id"; 
	
	/**
	 * Constructor for DBCollection
	 * Wraps MongoCollection
	 * This should only be used by DBCollectionFactory
	 * @param mc MongoClient for database connection
	 * @param db MongoDatabase for the database conection
	 * @param collection MongoColelction<Docuemnt> for the MongoCollection this wraps
	 */
	public DBCollection(MongoClient mc, MongoDatabase db, MongoCollection<Document> collection) {
		this.mc = mc;
		this.db = db;
		this.collection = collection;
	}
	
	/**
	 * Get document by id
	 * @param id String for database id
	 * @return Document with id in this collection
	 */
	public Document getDocumentById(String id) {
		Document doc = new Document(ID_KEY, new ObjectId(id));
		
		return collection.find(doc).first();
	}
	
	/**
	 * Delete the Document with this id from the database
	 * @param id String for database id
	 */
	public void deleteById(String id) {
		collection.deleteOne(new Document(ID_KEY, new ObjectId(id)));
	}
	
	/**
	 * Get all Documents in this collection
	 * @return Iterable<Document> of all Documents in this collection
	 */
	public Iterable<Document> getAllDocuments() {
		return collection.find();
	}
	
	/**
	 * Get document by key and value
	 * @param key String field of interest
	 * @param value String value of the field
	 * @return Iterable<Document> of the Documents that match this query
	 */
	public Iterable<Document> getDocumentsByValue(String key, String value) {
		Document docFilter = new Document(key, value);
		
		return collection.find(docFilter);
	}
	
	/**
	 * Get a single document by key and value
	 * @param key String field of interest
	 * @param value String value of the field
	 * @return first Document found matching this query
	 */
	public Document getDocumentByValue(String key, String value) {
		Document docFilter = new Document(key, value);
		
		return collection.find(docFilter).first();
	}
	
	/**
	 * Get documents matching multiple key value pairs
	 * @param docFilter Document containing key value pairs to search for
	 * @return Iterable<Document> of Documents matching this query
	 */
	public Iterable<Document> getDocumentsByFilter(Document docFilter) {
		return collection.find(docFilter);
	}
	
	/**
	 * Get first document matching multiple key value pairs
	 * @param docFilter Document containing key value pairs to search for
	 * @return first Document matching this query
	 */
	public Document getDocumentByFilter(Document docFilter) {
		return collection.find(docFilter).first();
	}
	
	/**
	 * Add a new document to the database
	 * @param doc Document to add to the database
	 */
	public void writeToDatabase(Document doc) {
		collection.insertOne(doc);
	}
	
	/**
	 * Add an existing document to the database
	 * @param id String id of the document in the database
	 * @param doc Document to add to the database
	 */
	public void writeToDatabase(String id, Document doc) {
		if (id == "") {
			writeToDatabase(doc);
		} else {
			collection.findOneAndReplace(new Document(ID_KEY, new ObjectId(id)), doc);
		}
	}
	
	/**
	 * Search multiple fields of mongo collection documents for text using $text index
	 * @param query String query
	 * @return Iterable<Document> of Documents matching this query
	 */
	public Iterable<Document> searchCollectionContents(String query) {
		Document searchDoc = new Document("$text", new Document("$search", query));
		
		return collection.find(searchDoc);
	}
}

package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

/**
 * Class storing user data and associated methods
 * @author eoin
 *
 */
public class User {
	private String username;
	private DBCollection dbColl;
	private String noteCollectionName = "Notes";
	
	/**
	 * Constructor for user object
	 * @param username String username for this user
	 */
	public User(String username) {
		this.username = username;
	}
	
	/**
	 * Set the username of this user
	 * @param username String username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get the username of the User
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get a list of Documents of this user's notes from the database
	 * @return List<Document> of Notes this user owns
	 */
	public List<Document> getUserNotes() {
		dbColl = DBCollectionFactory.getCollection(this.noteCollectionName);
		
		Document filter = new Document("user", username);
		Iterable<Document> docs = dbColl.getDocumentsByFilter(filter);
		
		List<Document> docL = new ArrayList<Document>();
		
		for (Document d: docs) {
			docL.add(d);
		}
		
		return docL;
		
	}
}

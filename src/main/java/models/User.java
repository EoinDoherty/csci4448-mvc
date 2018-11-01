package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import mongo.DatabaseManager;

public class User {
	private String username;
	private DatabaseManager db;
	private String noteCollectionName = "Notes";
	
	public User(String username) {
		this.username = username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public List<Document> getUserNotes() {
		db = new DatabaseManager();
		
		Document filter = new Document("user", username);
		Iterable<Document> docs = db.getDocumentsByFilter(noteCollectionName, filter);
		
		List<Document> docL = new ArrayList<Document>();
		
		for (Document d: docs) {
			docL.add(d);
		}
		
		return docL;
		
	}
}

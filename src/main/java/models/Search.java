package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

/**
 * Model for search page
 * @author eoin
 *
 */
public class Search {
	private String searchString;
	private DBCollection noteColl;
	private DBCollection userColl;
	private final String noteCollName = "Notes";
	private final String userCollName = "Users"; 
	private User user;
	
	/**
	 * Constructor for Search model object
	 * @param query String of the query for this search
	 * @param u User that ran this search
	 */
	public Search(String query, User u) {
		searchString = query;
		user = u;
		noteColl = DBCollectionFactory.getCollection(noteCollName);
		userColl = DBCollectionFactory.getCollection(userCollName);
	}
	
	/**
	 * Get a list of Note documents that match this search
	 * @return List<Docuemnt> of documents matching this search
	 */
	public List<Document> getSearchResults() {
		Iterable<Document> docs = noteColl.searchCollectionContents(searchString);
		
		List<Document> docL = new ArrayList<Document>();
		
		for (Document doc: docs) {
			docL.add(doc);
		}
		
		return docL;
	}
	
	/**
	 * Get a list of usernames of users that match this search
	 * @return List<String> of usernames matching this search
	 */
	public List<String> searchUsers() {
		Iterable<Document> docs = userColl.searchCollectionContents(searchString);
		
		List<String> userL = new ArrayList<String>();
		
		for (Document doc: docs) {
			userL.add(doc.getString("username"));
		}
		
		return userL;
	}
}

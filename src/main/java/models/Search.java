package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

public class Search {
	private String searchString;
	private DBCollection noteColl;
	private DBCollection userColl;
	private final String noteCollName = "Notes";
	private final String userCollName = "Users"; 
	private User user;
	
	public Search(String query, User u) {
		searchString = query;
		user = u;
		noteColl = DBCollectionFactory.getCollection(noteCollName);
		userColl = DBCollectionFactory.getCollection(userCollName);
	}
	
	public List<Document> getSearchResults() {
		Iterable<Document> docs = noteColl.searchCollectionContents(searchString);
		
		List<Document> docL = new ArrayList<Document>();
		
		for (Document doc: docs) {
			docL.add(doc);
		}
		
		return docL;
	}
	
	public List<String> searchUsers() {
		Iterable<Document> docs = userColl.searchCollectionContents(searchString);
		
		List<String> userL = new ArrayList<String>();
		
		for (Document doc: docs) {
			userL.add(doc.getString("username"));
		}
		
		return userL;
	}
	
	public static void main(String args[]) {
		Search s = new Search("user", null);
		
		List<String> users = s.searchUsers();
		
		for(String str: users) {
			System.out.println(str);
		}
	}
}

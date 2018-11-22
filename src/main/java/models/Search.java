package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

public class Search {
	private String searchString;
	private DBCollection dbColl;
	private String collectionName = "Notes";
	private User user;
	
	public Search(String query, User u) {
		searchString = query;
		user = u;
		dbColl = DBCollectionFactory.getCollection(collectionName);
	}
	
	public List<Document> getSearchResults() {
		Iterable<Document> docs = dbColl.searchCollectionContents(searchString, user.getUsername());
		
		List<Document> docL = new ArrayList<Document>();
		
		for (Document doc: docs) {
			docL.add(doc);
		}
		
		return docL;
	}
}

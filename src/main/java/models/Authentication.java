package models;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

public abstract class Authentication {
	protected String username;
	protected String password;
	protected final String collectionName = "Users";
	protected final String passwordField = "password";
	protected final String usernameField = "username";
	
	protected DBCollection coll;
	protected Document userDoc;
	
	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
		
		coll = DBCollectionFactory.getCollection(collectionName);
		userDoc = coll.getDocumentByValue(usernameField, username);
	}
	
	protected Boolean userExists() {
		return !(userDoc == null);
	}
	
	protected Boolean passwordValid() {
		return password.equals(userDoc.getString(passwordField));
	}
	
	public abstract Boolean isValid();
}

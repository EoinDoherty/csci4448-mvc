package models;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

/**
 * Abstract authentication class
 * extended by Login and Signup
 * @author eoin
 *
 */
public abstract class Authentication {
	protected String username;
	protected String password;
	protected final String collectionName = "Users";
	protected final String passwordField = "password";
	protected final String usernameField = "username";
	
	protected DBCollection coll;
	protected Document userDoc;
	
	/**
	 * Create Authentication object
	 * @param username Username used in authentication process
	 * @param password Password used in authentication process
	 */
	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
		
		coll = DBCollectionFactory.getCollection(collectionName);
		userDoc = coll.getDocumentByValue(usernameField, username);
	}
	
	/**
	 * Checks if the user is in the database
	 * @return Boolean
	 */
	protected Boolean userExists() {
		return !(userDoc == null);
	}
	
	/**
	 * Checks if the password for the user is correct
	 * @return Boolean
	 */
	protected Boolean passwordValid() {
		return password.equals(userDoc.getString(passwordField));
	}
	
	/**
	 * Checks if this authentication is valid
	 * Must be overridden 
	 * @return Boolean
	 */
	public abstract Boolean isValid();
}

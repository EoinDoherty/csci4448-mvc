package models;

import org.bson.Document;

/**
 * Model for signup process
 * @author eoin
 *
 */
public class Signup extends Authentication {
	
	/**
	 * Signup constructor
	 * @param username String username for signup
	 * @param password String password for signup
	 */
	public Signup(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Checks if this signup attempt is valid
	 * Returns Boolean
	 */
	public Boolean isValid() {
		return !userExists();
	}
	
	/**
	 * Creates an account using credentials from the constructors
	 */
	public void createAccount () {
		userDoc = new Document(usernameField, username);
		userDoc.put(passwordField, password);
		coll.writeToDatabase(userDoc);
	}
}

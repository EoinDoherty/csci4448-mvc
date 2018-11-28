package models;

import org.bson.Document;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

/**
 * Model for login
 * @author eoin
 *
 */
public class Login extends Authentication {
	
	/**
	 * Create Login object
	 * @param username String Username for login
	 * @param password String password for login
	 */
	public Login(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Checks if the login is valid
	 */
	public Boolean isValid() {
		return userExists() && passwordValid();
	}

}

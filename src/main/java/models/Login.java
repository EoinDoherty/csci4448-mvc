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
	
	public Login(String username, String password) {
		super(username, password);
	}
	
	public Boolean isValid() {
		return userExists() && passwordValid();
	}

}

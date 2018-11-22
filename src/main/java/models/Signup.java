package models;

import org.bson.Document;

public class Signup extends Authentication {
	
	public Signup(String username, String password) {
		super(username, password);
	}
	
	public Boolean isValid() {
		return !userExists();
	}
	
	public void createAccount () {
		userDoc = new Document(usernameField, username);
		userDoc.put(passwordField, password);
		coll.writeToDatabase(userDoc);
	}
}

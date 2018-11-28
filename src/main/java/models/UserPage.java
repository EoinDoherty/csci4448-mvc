package models;

import java.util.List;

import org.bson.Document;

/**
 * Model for user page
 * @author eoin
 *
 */
public class UserPage {
	
	private User sessionUser;
	private User pageUser;
	private Boolean owner = false;

	/**
	 * Constructor for UserPage model
	 * @param u User object for user who is viewing this page
	 * @param username String username of page's user
	 */
	public UserPage(User u, String username) {
		sessionUser = u;
		
		if (username.equals("") || username.equals(u.getUsername())) {
			pageUser = sessionUser;
			owner = true;
		} else {
			pageUser = new User(username);
			owner = false;
		}
	}
	
	/**
	 * Get the page's user's notes
	 * @return List<Document> List of notes this page's user owns
	 */
	public List<Document> getNotes() {
		return pageUser.getUserNotes();
	}
	
	/**
	 * Get username of page
	 * @return String username of the page
	 */
	public String getPageUserName() {
		return pageUser.getUsername();
	}
	
	/**
	 * Check if this is the page for the user viewing this page
	 * @return Boolean
	 */
	public Boolean isOwner() {
		return owner;
	}
}

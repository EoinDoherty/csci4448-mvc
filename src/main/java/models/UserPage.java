package models;

import java.util.List;

import org.bson.Document;

public class UserPage {
	
	private User sessionUser;
	private User pageUser;
	private Boolean owner = false;

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
	
	public List<Document> getNotes() {
		return pageUser.getUserNotes();
	}
	
	public String getPageUserName() {
		return pageUser.getUsername();
	}
	
	public Boolean isOwner() {
		return owner;
	}
}

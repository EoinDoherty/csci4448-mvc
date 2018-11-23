package models;

public class NoteChange {
	
	private String text;
	private String title;
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public String getText() {
		return text;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}

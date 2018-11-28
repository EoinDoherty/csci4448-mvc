package models;

/**
 * Bare-bones model for a note, used to store edits
 * @author eoin
 *
 */
public class NoteChange {
	
	private String text;
	private String title;
	private String username;
	
	/**
	 * Get username attribute of NoteChange
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get text of the NoteChange
	 * @return String text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Get title of the NoteChange
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the username of the NoteChange
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Set the text of the NoteChange
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Set the title of the NoteChange
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}

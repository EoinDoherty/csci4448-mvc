package models;

import org.bson.Document;
import org.bson.types.ObjectId;

import mongo.DBCollection;
import mongo.DBCollectionFactory;

/**
 * Model for note page
 * @author eoin
 *
 */
public class Note {
	private String dbId;
	private String collectionName = "Notes";
	private Document noteDoc;
	private DBCollection dbColl;
	private User user;

	/**
	 * Constructor for Note object
	 * @param u User object of the user who owns this note
	 * @param dbId String for database ID of note
	 */
	public Note(User u, String dbId) {
		this.dbId = dbId;
		user = u;
		
		dbColl = DBCollectionFactory.getCollection(this.collectionName);
		
		noteDoc = dbColl.getDocumentById(this.dbId);
		
		if (noteDoc == null) {
			this.dbId = "";
			noteDoc = new Document();
			
			setTitle("");
			setText("");
			setUsername(u.getUsername());
		}
	}
	
	/**
	 * Constructor for Note object
	 * @param u User object of the user who owns this note
	 */
	public Note(User u) {
		this.dbId = "";
		dbColl = DBCollectionFactory.getCollection(this.collectionName);
		user = u;
		
		noteDoc = new Document("title", "");
		noteDoc.put("text", "");
		noteDoc.put("user", u.getUsername());
	}
	
	/**
	 * Get stripped down, NoteChange version of this note
	 * @return NoteChange version of the note
	 */
	public NoteChange getNoteChange() {
		NoteChange nc = new NoteChange();
		nc.setUsername(getUsername());
		nc.setText(getText());
		nc.setTitle(getTitle());
		
		return nc;
	}
	
	/**
	 * Get String of note database ID
	 * @return String
	 */
	public String getId() {
		return dbId;
	}
	
	/**
	 * Get Document version of note
	 * @return Document
	 */
	public Document getNoteDoc() {
		return noteDoc;
	}
	
	/**
	 * Write this note to the database
	 */
	public void saveChanges() {
		dbColl.writeToDatabase(dbId, noteDoc);
		noteDoc = dbColl.getDocumentByFilter(noteDoc);
		ObjectId oid = (ObjectId) noteDoc.get("_id");
		System.out.println(dbId);
		dbId = oid.toHexString();
		System.out.println(dbId);
	}
	
	/**
	 * Write this note to the database 
	 * @param nc NoteChange containing note's data
	 */
	public void overwrite(NoteChange nc) {
		setTitle(nc.getTitle());
		setText(nc.getText());
		saveChanges();
	}
	
	/**
	 * Get the title of the note
	 * @return String
	 */
	public String getTitle() {
		return noteDoc.getString("title");
	}
	
	/**
	 * Get the text of the note
	 * @return String
	 */
	public String getText() {
		return noteDoc.getString("text");
	}
	
	/**
	 * Get the username of the note
	 * @return String
	 */
	public String getUsername() {
		return noteDoc.getString("user");
	}
	
	/**
	 * Set title of the note
	 * @param title String title of the note
	 */
	public void setTitle(String title) {
		noteDoc.put("title", title);
	}
	
	/**
	 * Set the text of the note
	 * @param text String text of the note
	 */
	public void setText(String text) {
		noteDoc.put("text", text);
	}
	
	/**
	 * Set the username for the note
	 * @param u String username of username owner
	 */
	public void setUsername(String u) {
		noteDoc.put("user", user.getUsername());
	}

	/**
	 * Delete this note from the database
	 */
	public void deleteNote() {
		if (dbId.equals("")) {
			setTitle("");
			setText("");
			return;
		}
		
		dbColl.deleteById(dbId);
	}
	
}

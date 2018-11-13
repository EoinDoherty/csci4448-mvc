package models;

import org.bson.Document;
import org.bson.types.ObjectId;

import mongo.DatabaseManager;

public class Note {
	private String dbId;
	private String collectionName = "Notes";
	private Document noteDoc;
	private DatabaseManager db;
	private User user;

	public Note(User u, String dbId) {
		this.dbId = dbId;
		user = u;
		
		db = new DatabaseManager();
		
		noteDoc = db.getDocumentById(this.collectionName, this.dbId);
		
		if (noteDoc == null) {
			this.dbId = "";
			noteDoc = new Document();
			
			setTitle("");
			setText("");
			setUsername(u.getUsername());
		}
	}
	
	public Note(User u) {
		this.dbId = "";
		db = new DatabaseManager();
		user = u;
		
		noteDoc = new Document("title", "");
		noteDoc.put("text", "");
		noteDoc.put("user", u.getUsername());
	}
	
	public NoteChange getNoteChange() {
		NoteChange nc = new NoteChange();
		nc.setText(getText());
		nc.setTitle(getTitle());
		
		return nc;
	}
	
	public String getId() {
		return dbId;
	}
	
	public Document getNoteDoc() {
		return noteDoc;
	}
	
	public void saveChanges() {
		db.writeToDatabase(collectionName, dbId, noteDoc);
		noteDoc = db.findDocumentByFilter(collectionName, noteDoc);
		ObjectId oid = (ObjectId) noteDoc.get("_id");
		System.out.println(dbId);
		dbId = oid.toHexString();
		System.out.println(dbId);
	}
	
	public void overwrite(NoteChange nc) {
		setTitle(nc.getTitle());
		setText(nc.getText());
		saveChanges();
	}
	
	public void setCollectionName(String name) {
		collectionName = name;
	}
	
	public String getTitle() {
		return noteDoc.getString("title");
	}
	
	public String getText() {
		return noteDoc.getString("text");
	}
	
	public String getUsername() {
		return noteDoc.getString("user");
	}
	
	public void setTitle(String title) {
		noteDoc.put("title", title);
	}
	
	public void setText(String text) {
		noteDoc.put("text", text);
	}
	
	public void setUsername(String u) {
		noteDoc.put("user", user.getUsername());
	}

	public void deleteNote() {
		if (dbId.equals("")) {
			setTitle("");
			setText("");
			return;
		}
		
		db.deleteById(this.collectionName, dbId);
	}
	
}

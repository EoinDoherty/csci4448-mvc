package models;

import org.bson.Document;
import org.bson.types.ObjectId;

import mongo.DatabaseManager;

public class Note {
	private String dbId;
	private String collectionName = "Notes";
	private Document noteDoc;
	private DatabaseManager db;

	public Note(String dbId) {
		this.dbId = dbId;
		
		db = new DatabaseManager();
		
		noteDoc = db.getDocumentById(this.collectionName, this.dbId);
	}
	
	public Note() {
		this.dbId = "";
		db = new DatabaseManager();
		
		noteDoc = new Document("title", "");
		noteDoc.put("text", "");
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
	
	public void setCollectionName(String name) {
		collectionName = name;
	}
	
	public String getTitle() {
		return noteDoc.getString("title");
	}
	
	public String getText() {
		return noteDoc.getString("text");
	}
	
	public void setTitle(String title) {
		noteDoc.put("title", title);
	}
	
	public void setText(String text) {
		noteDoc.put("text", text);
	}
	
}

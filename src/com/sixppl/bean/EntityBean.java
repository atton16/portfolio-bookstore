package com.sixppl.bean;

public class EntityBean {
	
	private String entityID;
	private String entityClass;		// Node or Edge
	private String entityType;		// Publication, Person (Author/Editor), Venue, School, directLink etc.
	private String entityCaption;	// Publication Title, Author Name, Journal, School, Relationship etc.
	
	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityCaption() {
		return entityCaption;
	}

	public void setEntityCaption(String entityCaption) {
		this.entityCaption = entityCaption;
	}

	public EntityBean() {
		// TODO Auto-generated constructor stub
	}

}

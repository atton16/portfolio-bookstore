package com.sixppl.bean;

public class EntityRecordBean {
	
	private long ID;
	private String entityID;
	private String attribute;
	private String value;
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EntityRecordBean() {
		// TODO Auto-generated constructor stub
	}

}

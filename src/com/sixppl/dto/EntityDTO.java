package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Stack;

public class EntityDTO {
	private long ID;
	private String entityID;		// Format = 1 prefix + 20 ID e.g. A1 is Author with ID = 1
	private String entityClass;		// Node or Edge
	private String entityType;		// Publication, Person (Author/Editor), Venue, School, directLink etc.
	private String entityCaption;	// Publication Title, Author Name, Journal, School, Relationship etc.

	public EntityDTO() {
		this.ID = 0;
		this.entityID = null;
		this.entityClass = null;
		this.entityType = null;
		this.entityCaption = null;
	}
	
	public EntityDTO(long ID, String entityID, String entityClass, String entityType, String entityCaption) {
		this.ID = ID;
		this.entityID = entityID;
		this.entityClass = entityClass;
		this.entityType = entityType;
		this.entityCaption = entityCaption;
	}
	
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

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityCaption() {
		return entityCaption;
	}

	public void setEntityCaption(String entityCaption) {
		this.entityCaption = entityCaption;
	}
	
	public static boolean containsID(ArrayList<EntityDTO> entityList, long ID) {
	    for (EntityDTO node : entityList) {
	        if (node.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEntityID(ArrayList<EntityDTO> entityList, String entityID) {
	    for (EntityDTO node : entityList) {
	        if (node.getEntityID().equals(entityID)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsID(Stack<EntityDTO> entityList, long ID) {
	    for (EntityDTO node : entityList) {
	        if (node.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEntityID(Stack<EntityDTO> entityList, String entityID) {
	    for (EntityDTO node : entityList) {
	        if (node.getEntityID().equals(entityID)) {
	            return true;
	        }
	    }
	    return false;
	}
}

package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Stack;

public class EntityDTO {
	private long ID;
	private String entityID;		// Format = 1 prefix + 20 ID e.g. A1 is Author with ID = 1
	private String entityClass;		// Node or Edge
	private String entityType;		// Publication, Person (Author/Editor), Venue, School, directLink etc.
	private String entityCaption;	// Publication Title, Author Name, Journal, School, Relationship etc.

	private ArrayList<EntityDTO> nodeList;
	private Stack<EntityDTO> nodeStack;
	
	public EntityDTO() {
		// TODO Auto-generated constructor stub
		this.ID = 0;
		this.entityID = null;
		this.entityClass = null;
		this.entityType = null;
		this.entityCaption = null;
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
	
	public ArrayList<EntityDTO> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<EntityDTO> nodeList) {
		this.nodeList = nodeList;
	}

	public Stack<EntityDTO> getNodeStack() {
		return nodeStack;
	}

	public void setNodeStack(Stack<EntityDTO> nodeStack) {
		this.nodeStack = nodeStack;
	}

	public static boolean containsID(ArrayList<EntityDTO> nodeList, long ID) {
	    for (EntityDTO node : nodeList) {
	        if (node.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEntityID(ArrayList<EntityDTO> nodeList, String entityID) {
	    for (EntityDTO node : nodeList) {
	        if (node.getEntityID().equals(entityID)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsID(Stack<EntityDTO> nodeList, long ID) {
	    for (EntityDTO node : nodeList) {
	        if (node.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEntityID(Stack<EntityDTO> nodeList, String entityID) {
	    for (EntityDTO node : nodeList) {
	        if (node.getEntityID().equals(entityID)) {
	            return true;
	        }
	    }
	    return false;
	}
}

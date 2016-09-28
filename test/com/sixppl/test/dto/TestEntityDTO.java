package com.sixppl.test.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sixppl.dto.EntityDTO;

public class TestEntityDTO {

	ArrayList<EntityDTO> entityList;
	Stack<EntityDTO> entityStack;
	EntityDTO entity1;
	EntityDTO entity2;
	EntityDTO entity3;
	EntityDTO entity4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		entityList = new ArrayList<EntityDTO>();
		entityStack = new Stack<EntityDTO>();
		entity1 = new EntityDTO(1, "P1", "Node", "Publication", "Article Title");
		entity2 = new EntityDTO(2, "A1", "Node", "Author", "Firstname Lastname");
		entity3 = new EntityDTO(3, "A2", "Node", "Author", "Article Title");
		entity4 = new EntityDTO(4, "A3", "Node", "Author", "Firstname Lastname");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContainsIDinArrayList() {
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(EntityDTO.containsID(entityList, entity1.getID()));
		assertFalse(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.add(entity1);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity1.getID()));
		// Expected false -> Should not found any matched Entity of not inserted Entity
		assertFalse(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.add(entity2);
		// Expected true -> Should found matched of inserted Entity in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity1.getID()));
		assertTrue(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.remove(0);
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsID(entityList, entity1.getID()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity2.getID()));
	}
	
	@Test
	public void testContainsIDinStack() {
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(EntityDTO.containsID(entityStack, entity1.getID()));
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.push(entity1);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity1.getID()));
		// Expected false -> Should not found any matched EntityID of not inserted Entity
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.push(entity2);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity1.getID()));
		assertTrue(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.pop();
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity1.getID()));
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
	}

	@Test
	public void testContainsEntityIDinArrayList() {
		// Expected false -> Should not found any matched EntityID in empty ArrayList
		assertFalse(EntityDTO.containsEntityID(entityList, entity1.getEntityID()));
		assertFalse(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.add(entity1);
		// Expected true -> Should found matched EntityID in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity1.getEntityID()));
		// Expected false -> Should not found any matched Entity of not inserted Entity
		assertFalse(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.add(entity2);
		// Expected true -> Should found matched of inserted Entity in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity1.getEntityID()));
		assertTrue(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.remove(0);
		// Expected false -> Should not found any matched Entity of removed Entity
		assertFalse(EntityDTO.containsEntityID(entityList, entity1.getEntityID()));
		// Expected true -> Should found matched Entity in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
	}
	
	@Test
	public void testContainsEntityIDinStack() {
		// Expected false -> Should not found any matched EntityID in empty Stack
		assertFalse(EntityDTO.containsEntityID(entityStack, entity1.getEntityID()));
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.push(entity1);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity1.getEntityID()));
		// Expected false -> Should not found any matched EntityID of not inserted Entity
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.push(entity2);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity1.getEntityID()));
		assertTrue(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.pop();
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity1.getEntityID()));
		// Expected false -> Should not found any matched EntityID of removed Stack
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
	}
	
	@Test
	public void testContainsCaptioninArrayList() {
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(EntityDTO.containsCaption(entityList, entity1.getEntityCaption()));
		assertFalse(EntityDTO.containsCaption(entityList, entity2.getEntityCaption()));
		entityList.add(entity1);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsCaption(entityList, entity1.getEntityCaption()));
		// Expected false -> Should not found any matched Entity of not inserted Entity
		assertFalse(EntityDTO.containsCaption(entityList, entity2.getEntityCaption()));
		entityList.add(entity2);
		// Expected true -> Should found matched of inserted Entity in ArrayList
		assertTrue(EntityDTO.containsCaption(entityList, entity1.getEntityCaption()));
		assertTrue(EntityDTO.containsCaption(entityList, entity2.getEntityCaption()));
		entityList.remove(0);
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsCaption(entityList, entity1.getEntityCaption()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsCaption(entityList, entity2.getEntityCaption()));
	}
	
	@Test
	public void testContainsCaptioninStack() {
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(EntityDTO.containsCaption(entityStack, entity1.getEntityCaption()));
		assertFalse(EntityDTO.containsCaption(entityStack, entity2.getEntityCaption()));
		entityStack.push(entity1);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsCaption(entityStack, entity1.getEntityCaption()));
		// Expected false -> Should not found any matched EntityID of not inserted Entity
		assertFalse(EntityDTO.containsCaption(entityStack, entity2.getEntityCaption()));
		entityStack.push(entity2);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsCaption(entityStack, entity1.getEntityCaption()));
		assertTrue(EntityDTO.containsCaption(entityStack, entity2.getEntityCaption()));
		entityStack.pop();
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsCaption(entityStack, entity1.getEntityCaption()));
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsCaption(entityStack, entity2.getEntityCaption()));
	}
	
	@Test
	public void testContainsEntityinArrayList() {
		assertFalse(EntityDTO.containsEntity(entityList, entity1));
		assertFalse(EntityDTO.containsEntity(entityList, entity2));
		assertFalse(EntityDTO.containsEntity(entityList, entity3));
		assertFalse(EntityDTO.containsEntity(entityList, entity4));
		entityList.add(entity1);
		assertTrue(EntityDTO.containsEntity(entityList, entity1));
		assertFalse(EntityDTO.containsEntity(entityList, entity2));
		assertFalse(EntityDTO.containsEntity(entityList, entity3));
		assertFalse(EntityDTO.containsEntity(entityList, entity4));
		entityList.add(entity2);
		assertTrue(EntityDTO.containsEntity(entityList, entity1));
		assertTrue(EntityDTO.containsEntity(entityList, entity2));
		assertFalse(EntityDTO.containsEntity(entityList, entity3));
		assertTrue(EntityDTO.containsEntity(entityList, entity4));
		entityList.remove(0);
		assertFalse(EntityDTO.containsEntity(entityList, entity1));
		assertTrue(EntityDTO.containsEntity(entityList, entity2));
		assertFalse(EntityDTO.containsEntity(entityList, entity3));
		assertTrue(EntityDTO.containsEntity(entityList, entity4));
	}
	
	@Test
	public void testContainsEntityinStack() {
		assertFalse(EntityDTO.containsEntity(entityStack, entity1));
		assertFalse(EntityDTO.containsEntity(entityStack, entity2));
		assertFalse(EntityDTO.containsEntity(entityStack, entity3));
		assertFalse(EntityDTO.containsEntity(entityStack, entity4));
		entityStack.push(entity1);
		assertTrue(EntityDTO.containsEntity(entityStack, entity1));
		assertFalse(EntityDTO.containsEntity(entityStack, entity2));
		assertFalse(EntityDTO.containsEntity(entityStack, entity3));
		assertFalse(EntityDTO.containsEntity(entityStack, entity4));
		entityStack.push(entity2);
		assertTrue(EntityDTO.containsEntity(entityStack, entity1));
		assertTrue(EntityDTO.containsEntity(entityStack, entity2));
		assertFalse(EntityDTO.containsEntity(entityStack, entity3));
		assertTrue(EntityDTO.containsEntity(entityStack, entity4));
		entityStack.pop();
		assertTrue(EntityDTO.containsEntity(entityStack, entity1));
		assertFalse(EntityDTO.containsEntity(entityStack, entity2));
		assertFalse(EntityDTO.containsEntity(entityStack, entity3));
		assertFalse(EntityDTO.containsEntity(entityStack, entity4));
	}
	
	@Test
	public void testFindEntityinArrayList() {
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity1)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity2)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity3)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity4)));
		entityList.add(entity1);
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity1)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity2)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity3)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity4)));
		entityList.add(entity2);
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity1)));
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity2)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity3)));
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity4)));
		entityList.remove(0);
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity1)));
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity2)));
		assertFalse(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity3)));
		assertTrue(EntityDTO.containsEntity(entityList, EntityDTO.findEntity(entityList, entity4)));
	}
	
	@Test
	public void testFindEntityinStack() {
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity1)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity2)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity3)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity4)));
		entityStack.push(entity1);
		assertTrue(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity1)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity2)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity3)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity4)));
		entityStack.push(entity2);
		assertTrue(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity1)));
		assertTrue(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity2)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity3)));
		assertTrue(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity4)));
		entityStack.pop();
		assertTrue(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity1)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity2)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity3)));
		assertFalse(EntityDTO.containsEntity(entityStack, EntityDTO.findEntity(entityStack, entity4)));
	}
}

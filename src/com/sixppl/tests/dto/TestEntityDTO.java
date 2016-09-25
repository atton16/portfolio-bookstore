package com.sixppl.tests.dto;

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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContainsIDinArrayList() {
		ArrayList<EntityDTO> entityList = new ArrayList<EntityDTO>();
		EntityDTO entity = new EntityDTO(1, "P1", "Node", "Publication", "Article Title");
		EntityDTO entity2 = new EntityDTO(2, "A1", "Node", "Author", "Firstname Lastname");
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(EntityDTO.containsID(entityList, entity.getID()));
		assertFalse(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.add(entity);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity.getID()));
		// Expected false -> Should not found any matched Entity of not inserted Entity
		assertFalse(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.add(entity2);
		// Expected true -> Should found matched of inserted Entity in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity.getID()));
		assertTrue(EntityDTO.containsID(entityList, entity2.getID()));
		entityList.remove(0);
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsID(entityList, entity.getID()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(EntityDTO.containsID(entityList, entity2.getID()));
	}
	
	@Test
	public void testContainsIDinStack() {
		Stack<EntityDTO> entityStack = new Stack<EntityDTO>();
		EntityDTO entity = new EntityDTO(1, "P1", "Node", "Publication", "Article Title");
		EntityDTO entity2 = new EntityDTO(2, "A1", "Node", "Author", "Firstname Lastname");
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(EntityDTO.containsID(entityStack, entity.getID()));
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.push(entity);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity.getID()));
		// Expected false -> Should not found any matched EntityID of not inserted Entity
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.push(entity2);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity.getID()));
		assertTrue(EntityDTO.containsID(entityStack, entity2.getID()));
		entityStack.pop();
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsID(entityStack, entity.getID()));
		// Expected false -> Should not found any matched ID of removed Entity
		assertFalse(EntityDTO.containsID(entityStack, entity2.getID()));
	}

	@Test
	public void testContainsEntityIDinArrayList() {
		ArrayList<EntityDTO> entityList = new ArrayList<EntityDTO>();
		EntityDTO entity = new EntityDTO(1, "P1", "Node", "Publication", "Article Title");
		EntityDTO entity2 = new EntityDTO(2, "A1", "Node", "Author", "Firstname Lastname");
		// Expected false -> Should not found any matched EntityID in empty ArrayList
		assertFalse(EntityDTO.containsEntityID(entityList, entity.getEntityID()));
		assertFalse(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.add(entity);
		// Expected true -> Should found matched EntityID in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity.getEntityID()));
		// Expected false -> Should not found any matched Entity of not inserted Entity
		assertFalse(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.add(entity2);
		// Expected true -> Should found matched of inserted Entity in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity.getEntityID()));
		assertTrue(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
		entityList.remove(0);
		// Expected false -> Should not found any matched Entity of removed Entity
		assertFalse(EntityDTO.containsEntityID(entityList, entity.getEntityID()));
		// Expected true -> Should found matched Entity in ArrayList
		assertTrue(EntityDTO.containsEntityID(entityList, entity2.getEntityID()));
	}
	
	@Test
	public void testContainsEntityIDinStack() {
		Stack<EntityDTO> entityStack = new Stack<EntityDTO>();
		EntityDTO entity = new EntityDTO(1, "P1", "Node", "Publication", "Article Title");
		EntityDTO entity2 = new EntityDTO(2, "A1", "Node", "Author", "Firstname Lastname");
		// Expected false -> Should not found any matched EntityID in empty Stack
		assertFalse(EntityDTO.containsEntityID(entityStack, entity.getEntityID()));
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.push(entity);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity.getEntityID()));
		// Expected false -> Should not found any matched EntityID of not inserted Entity
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.push(entity2);
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity.getEntityID()));
		assertTrue(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
		entityStack.pop();
		// Expected true -> Should found matched of inserted Entity in Stack
		assertTrue(EntityDTO.containsEntityID(entityStack, entity.getEntityID()));
		// Expected false -> Should not found any matched EntityID of removed Stack
		assertFalse(EntityDTO.containsEntityID(entityStack, entity2.getEntityID()));
	}
}

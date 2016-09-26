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

import com.sixppl.dto.GraphOutputDTO;

public class TestGraphOutputDTO {

	ArrayList<GraphOutputDTO> graphOutputList;
	Stack<GraphOutputDTO> graphOutputStack;
	GraphOutputDTO graphOutput1;
	GraphOutputDTO graphOutput2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		graphOutputList = new ArrayList<GraphOutputDTO>();
		graphOutputStack = new Stack<GraphOutputDTO>();
		graphOutput1 = new GraphOutputDTO(1, 1, "P1", "Article Title", 3, "E1", "authored by", 2, "A1", "Firstname Lastname");
		graphOutput2 = new GraphOutputDTO(2, 1, "P1", "Article Title", 5, "E2", "published in", 4, "V1", "Journal");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContainsIDinArrayList() {
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(GraphOutputDTO.containsID(graphOutputList, graphOutput1.getID()));
		assertFalse(GraphOutputDTO.containsID(graphOutputList, graphOutput2.getID()));
		graphOutputList.add(graphOutput1);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphOutputDTO.containsID(graphOutputList, graphOutput1.getID()));
		// Expected false -> Should not found any matched GraphOutput of not inserted GraphOutput
		assertFalse(GraphOutputDTO.containsID(graphOutputList, graphOutput2.getID()));
		graphOutputList.add(graphOutput2);
		// Expected true -> Should found matched of inserted GraphOutput in ArrayList
		assertTrue(GraphOutputDTO.containsID(graphOutputList, graphOutput1.getID()));
		assertTrue(GraphOutputDTO.containsID(graphOutputList, graphOutput2.getID()));
		graphOutputList.remove(0);
		// Expected false -> Should not found any matched ID of removed GraphOutput
		assertFalse(GraphOutputDTO.containsID(graphOutputList, graphOutput1.getID()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphOutputDTO.containsID(graphOutputList, graphOutput2.getID()));
	}
	
	@Test
	public void testContainsIDinStack() {
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(GraphOutputDTO.containsID(graphOutputStack, graphOutput1.getID()));
		assertFalse(GraphOutputDTO.containsID(graphOutputStack, graphOutput2.getID()));
		graphOutputStack.push(graphOutput1);
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsID(graphOutputStack, graphOutput1.getID()));
		// Expected false -> Should not found any matched Edge of not inserted GraphOutput
		assertFalse(GraphOutputDTO.containsID(graphOutputStack, graphOutput2.getID()));
		graphOutputStack.push(graphOutput2);
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsID(graphOutputStack, graphOutput1.getID()));
		assertTrue(GraphOutputDTO.containsID(graphOutputStack, graphOutput2.getID()));
		graphOutputStack.pop();
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsID(graphOutputStack, graphOutput1.getID()));
		// Expected false -> Should not found any matched ID of removed GraphOutput
		assertFalse(GraphOutputDTO.containsID(graphOutputStack, graphOutput2.getID()));
	}

	@Test
	public void testContainsEdgeinArrayList() {
		// Expected false -> Should not found any matched Edge in empty ArrayList
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput1.getEdge()));
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput2.getEdge()));
		graphOutputList.add(graphOutput1);
		// Expected true -> Should found matched Edge in ArrayList
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput1.getEdge()));
		// Expected false -> Should not found any matched GraphOutput of not inserted GraphOutput
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput2.getEdge()));
		graphOutputList.add(graphOutput2);
		// Expected true -> Should found matched of inserted GraphOutput in ArrayList
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput1.getEdge()));
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput2.getEdge()));
		graphOutputList.remove(0);
		// Expected false -> Should not found any matched GraphOutput of removed GraphOutput
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput1.getEdge()));
		// Expected true -> Should found matched GraphOutput in ArrayList
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputList, graphOutput2.getEdge()));
	}
	
	@Test
	public void testContainsEdgeinStack() {
		// Expected false -> Should not found any matched Edge in empty Stack
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput1.getEdge()));
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput2.getEdge()));
		graphOutputStack.push(graphOutput1);
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput1.getEdge()));
		// Expected false -> Should not found any matched Edge of not inserted GraphOutput
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput2.getEdge()));
		graphOutputStack.push(graphOutput2);
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput1.getEdge()));
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput2.getEdge()));
		graphOutputStack.pop();
		// Expected true -> Should found matched of inserted GraphOutput in Stack
		assertTrue(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput1.getEdge()));
		// Expected false -> Should not found any matched Edge of removed Stack
		assertFalse(GraphOutputDTO.containsEdgeID(graphOutputStack, graphOutput2.getEdge()));
	}
}

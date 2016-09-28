package com.sixppl.test.dto;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sixppl.dto.GraphDTO;

public class TestGraphDTO {
	
	ArrayList<GraphDTO> graphList;
	Stack<GraphDTO> graphStack;
	GraphDTO graph1;
	GraphDTO graph2;
	GraphDTO graph3;
	GraphDTO graph4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		graphList = new ArrayList<GraphDTO>();
		graphStack = new Stack<GraphDTO>();
		graph1 = new GraphDTO(1, "P1", "E1", "A1");
		graph2 = new GraphDTO(2, "P1", "E2", "V1");
		graph3 = new GraphDTO(3, "P2", "E2", "V1");
		graph4 = new GraphDTO(4, "P1", "E4", "V1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContainsIDinArrayList() {
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(GraphDTO.containsID(graphList, graph1.getID()));
		assertFalse(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.add(graph1);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph1.getID()));
		// Expected false -> Should not found any matched Graph of not inserted Graph
		assertFalse(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.add(graph2);
		// Expected true -> Should found matched of inserted Graph in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph1.getID()));
		assertTrue(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.remove(0);
		// Expected false -> Should not found any matched ID of removed Graph
		assertFalse(GraphDTO.containsID(graphList, graph1.getID()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph2.getID()));
	}
	
	@Test
	public void testContainsIDinStack() {
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(GraphDTO.containsID(graphStack, graph1.getID()));
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.push(graph1);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph1.getID()));
		// Expected false -> Should not found any matched Edge of not inserted Graph
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.push(graph2);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph1.getID()));
		assertTrue(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.pop();
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph1.getID()));
		// Expected false -> Should not found any matched ID of removed Graph
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
	}

	@Test
	public void testContainsEdgeIDinArrayList() {
		// Expected false -> Should not found any matched Edge in empty ArrayList
		assertFalse(GraphDTO.containsEdgeID(graphList, graph1.getEdge()));
		assertFalse(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.add(graph1);
		// Expected true -> Should found matched Edge in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph1.getEdge()));
		// Expected false -> Should not found any matched Graph of not inserted Graph
		assertFalse(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.add(graph2);
		// Expected true -> Should found matched of inserted Graph in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph1.getEdge()));
		assertTrue(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.remove(0);
		// Expected false -> Should not found any matched Graph of removed Graph
		assertFalse(GraphDTO.containsEdgeID(graphList, graph1.getEdge()));
		// Expected true -> Should found matched Graph in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
	}
	
	@Test
	public void testContainsEdgeIDinStack() {
		// Expected false -> Should not found any matched Edge in empty Stack
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph1.getEdge()));
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.push(graph1);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph1.getEdge()));
		// Expected false -> Should not found any matched Edge of not inserted Graph
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.push(graph2);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph1.getEdge()));
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.pop();
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph1.getEdge()));
		// Expected false -> Should not found any matched Edge of removed Stack
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
	}
}

package com.sixppl.tests.dto;

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
		ArrayList<GraphDTO> graphList = new ArrayList<GraphDTO>();
		GraphDTO graph = new GraphDTO(1, "P1", "E1", "A1");
		GraphDTO graph2 = new GraphDTO(2, "P1", "E2", "V1");
		// Expected false -> Should not found any matched ID in empty ArrayList
		assertFalse(GraphDTO.containsID(graphList, graph.getID()));
		assertFalse(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.add(graph);
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph.getID()));
		// Expected false -> Should not found any matched Graph of not inserted Graph
		assertFalse(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.add(graph2);
		// Expected true -> Should found matched of inserted Graph in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph.getID()));
		assertTrue(GraphDTO.containsID(graphList, graph2.getID()));
		graphList.remove(0);
		// Expected false -> Should not found any matched ID of removed Graph
		assertFalse(GraphDTO.containsID(graphList, graph.getID()));
		// Expected true -> Should found matched ID in ArrayList
		assertTrue(GraphDTO.containsID(graphList, graph2.getID()));
	}
	
	@Test
	public void testContainsIDinStack() {
		Stack<GraphDTO> graphStack = new Stack<GraphDTO>();
		GraphDTO graph = new GraphDTO(1, "P1", "E1", "A1");
		GraphDTO graph2 = new GraphDTO(2, "P1", "E2", "V1");
		// Expected false -> Should not found any matched ID in empty Stack
		assertFalse(GraphDTO.containsID(graphStack, graph.getID()));
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.push(graph);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph.getID()));
		// Expected false -> Should not found any matched Edge of not inserted Graph
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.push(graph2);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph.getID()));
		assertTrue(GraphDTO.containsID(graphStack, graph2.getID()));
		graphStack.pop();
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsID(graphStack, graph.getID()));
		// Expected false -> Should not found any matched ID of removed Graph
		assertFalse(GraphDTO.containsID(graphStack, graph2.getID()));
	}

	@Test
	public void testContainsEdgeinArrayList() {
		ArrayList<GraphDTO> graphList = new ArrayList<GraphDTO>();
		GraphDTO graph = new GraphDTO(1, "P1", "E1", "A1");
		GraphDTO graph2 = new GraphDTO(2, "P1", "E2", "V1");
		// Expected false -> Should not found any matched Edge in empty ArrayList
		assertFalse(GraphDTO.containsEdgeID(graphList, graph.getEdge()));
		assertFalse(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.add(graph);
		// Expected true -> Should found matched Edge in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph.getEdge()));
		// Expected false -> Should not found any matched Graph of not inserted Graph
		assertFalse(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.add(graph2);
		// Expected true -> Should found matched of inserted Graph in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph.getEdge()));
		assertTrue(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
		graphList.remove(0);
		// Expected false -> Should not found any matched Graph of removed Graph
		assertFalse(GraphDTO.containsEdgeID(graphList, graph.getEdge()));
		// Expected true -> Should found matched Graph in ArrayList
		assertTrue(GraphDTO.containsEdgeID(graphList, graph2.getEdge()));
	}
	
	@Test
	public void testContainsEdgeinStack() {
		Stack<GraphDTO> graphStack = new Stack<GraphDTO>();
		GraphDTO graph = new GraphDTO(1, "P1", "E1", "A1");
		GraphDTO graph2 = new GraphDTO(2, "P1", "E2", "V1");
		// Expected false -> Should not found any matched Edge in empty Stack
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph.getEdge()));
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.push(graph);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph.getEdge()));
		// Expected false -> Should not found any matched Edge of not inserted Graph
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.push(graph2);
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph.getEdge()));
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
		graphStack.pop();
		// Expected true -> Should found matched of inserted Graph in Stack
		assertTrue(GraphDTO.containsEdgeID(graphStack, graph.getEdge()));
		// Expected false -> Should not found any matched Edge of removed Stack
		assertFalse(GraphDTO.containsEdgeID(graphStack, graph2.getEdge()));
	}
}

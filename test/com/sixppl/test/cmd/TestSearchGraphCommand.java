package com.sixppl.test.cmd;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sixppl.cmd.Command;
import com.sixppl.cmd.SearchGraphCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestSearchGraphCommand {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test // Test if search run successfully and database has data
	public void testExecute_Author() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("type", new String[]{"Author"});
		inputMap.put("keyword", new String[]{""});
		
		// The actual test
		Command searchGraphCmd = new SearchGraphCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			searchGraphCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}
		
		// Output Check
		String successData = (String) request.getAttribute("data");
		String successMatchedKeyword = (String) request.getAttribute("matchedNode");
		Boolean success = !successData.equals("{\"nodes\": [],\"edges\": []}") && !successMatchedKeyword.equals("{\"nodes\": []}");
		if(successData.equals("{\"nodes\": [],\"edges\": []}") || successMatchedKeyword.equals("{\"nodes\": []}"))
			fail("No data");

		// Expecting success result
		assertTrue(success);
	}

	@Test // Test if search run successfully and database has data
	public void testExecute_Publication() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("type", new String[]{"Publication"});
		inputMap.put("keyword", new String[]{""});
		
		// The actual test
		Command searchGraphCmd = new SearchGraphCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			searchGraphCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}
		
		// Output Check
		String successData = (String) request.getAttribute("data");
		String successMatchedKeyword = (String) request.getAttribute("matchedNode");
		Boolean success = !successData.equals("{\"nodes\": [],\"edges\": []}") && !successMatchedKeyword.equals("{\"nodes\": []}");
		if(successData.equals("{\"nodes\": [],\"edges\": []}") || successMatchedKeyword.equals("{\"nodes\": []}"))
			fail("No data");

		// Expecting success result
		assertTrue(success);
	}
	
	@Test // Test if search run successfully and database has data
	public void testExecute_Venue() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("type", new String[]{"Venue"});
		inputMap.put("keyword", new String[]{""});
		
		// The actual test
		Command searchGraphCmd = new SearchGraphCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			searchGraphCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}
		
		// Output Check
		String successData = (String) request.getAttribute("data");
		String successMatchedKeyword = (String) request.getAttribute("matchedNode");
		Boolean success = !successData.equals("{\"nodes\": [],\"edges\": []}") && !successMatchedKeyword.equals("{\"nodes\": []}");
		if(successData.equals("{\"nodes\": [],\"edges\": []}") || successMatchedKeyword.equals("{\"nodes\": []}"))
			fail("No data");

		// Expecting success result
		assertTrue(success);
	}
	
	@Test // Test if search run successfully and database has data
	public void testExecute_School() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("type", new String[]{"School"});
		inputMap.put("keyword", new String[]{""});
		
		// The actual test
		Command searchGraphCmd = new SearchGraphCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			searchGraphCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}
		
		// Output Check
		String successData = (String) request.getAttribute("data");
		String successMatchedKeyword = (String) request.getAttribute("matchedNode");
		Boolean success = !successData.equals("{\"nodes\": [],\"edges\": []}") && !successMatchedKeyword.equals("{\"nodes\": []}");
		if(successData.equals("{\"nodes\": [],\"edges\": []}") || successMatchedKeyword.equals("{\"nodes\": []}"))
			fail("No data");

		// Expecting success result
		assertTrue(success);
	}
}

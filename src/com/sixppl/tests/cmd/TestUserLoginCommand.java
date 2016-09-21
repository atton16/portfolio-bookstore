package com.sixppl.tests.cmd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.sixppl.cmd.*;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class TestUserLoginCommand {

	@Before
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@Test
	public void testValidCredentials() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"admin"});
		inputMap.put("password", new String[]{"admin"});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting success result
		assertTrue(success);
	}

	@Test
	public void testSQLInjectionOnUsername() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"' OR '1"});
		inputMap.put("password", new String[]{"admin"});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}

	@Test
	public void testSQLInjectionOnPassword() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"admin"});
		inputMap.put("password", new String[]{"' OR '1"});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}

	@Test
	public void testEmptyUsername() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{""});
		inputMap.put("password", new String[]{"admin"});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}
	
	@Test
	public void testEmptyPassword() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"admin"});
		inputMap.put("password", new String[]{""});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}
	
	@Test
	public void testNULLUsername() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{});
		inputMap.put("password", new String[]{""});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}
	
	@Test
	public void testNULLPassword() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"admin"});
		inputMap.put("password", new String[]{});

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		}

		// Output Check
		Boolean success = (Boolean) request.getAttribute("success");
		if(success == null)
			fail("Internal code error");

		// Expecting non-success result
		assertFalse(success);
	}
}

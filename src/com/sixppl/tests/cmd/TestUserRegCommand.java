package com.sixppl.tests.cmd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.sixppl.cmd.Command;
import com.sixppl.cmd.UserLoginCommand;
import com.sixppl.cmd.UserRegCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestUserRegCommand {
	@Before
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@Test
	public void testValidRegistration() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"tt"});
		inputMap.put("password", new String[]{"tt"});
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
		

		// The actual test
		Command userCmd = new UserRegCommand();
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
		inputMap.put("password", new String[]{"cloud"});
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
	public void testSQLInjectionOnEmail() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"cloud"});
		inputMap.put("password", new String[]{"cloud"});
		inputMap.put("email", new String[]{"' OR '1"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
	public void testSameUsername() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"admin"});
		inputMap.put("password", new String[]{"admin"});
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
	public void testEmptyEmail() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"cloud"});
		inputMap.put("password", new String[]{"cloud"});
		inputMap.put("email", new String[]{""});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});
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
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});

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
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});

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
	public void testNULLEmail() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("username", new String[]{"cloud"});
		inputMap.put("password", new String[]{"cloud"});
		inputMap.put("email", new String[]{"zhangyuny@gmail.com"});
		inputMap.put("address", new String[]{"ATP"});
		inputMap.put("ccn", new String[]{"0404270801"});

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

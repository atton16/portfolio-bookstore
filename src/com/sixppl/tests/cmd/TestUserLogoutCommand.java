package com.sixppl.tests.cmd;

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
import com.sixppl.cmd.UserConfirmCommand;
import com.sixppl.cmd.UserLogoutCommand;
import com.sixppl.cmd.UserProfileCommand;
import com.sixppl.cmd.UserRegCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestUserLogoutCommand {
	@Before
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@Test
	public void testUserLogout() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		String sessionId = null;
		inputMap.put(sessionId, new String[]{"5642232"});
		
		// The actual test
		System.out.println("start test UserLogoutCommand!!");
		Command userLogout = new UserLogoutCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userLogout.execute(request, null);
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
}

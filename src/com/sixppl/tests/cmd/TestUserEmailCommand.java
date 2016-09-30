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

import com.sixppl.cmd.UserEmailCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestUserEmailCommand {
	@Before
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@Test
	public void testUserEmail() {
		// Constructing test input
		String testemail = "jessieraspi2016@gmail.com";
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("email", new String[]{testemail});
		
		// The actual test
		System.out.println("start test sending email to the one you indicated in testemail!!");
		UserEmailCommand userEmail = new UserEmailCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userEmail.execute(request, null);
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

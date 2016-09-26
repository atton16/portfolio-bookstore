package com.sixppl.tests.cmd;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.sixppl.cmd.Command;
import com.sixppl.cmd.UserConfirmCommand;
import com.sixppl.cmd.UserProfileCommand;
import com.sixppl.cmd.UserRegCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestUserConfirmCommand {
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
	}

	@Test
	public void testUserConfirm() {
		// Constructing test input
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		inputMap.put("token", new String[]{"38891d14-15b8-4cf2-"});
		
		// The actual test
		System.out.println("start test UserConfirmCommand!!");
		UserConfirmCommand userConfirm = new UserConfirmCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			userConfirm.execute(request, null);
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

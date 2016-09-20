package com.sixppl.tests.cmd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.*;

import org.junit.Test;

import com.sixppl.cmd.Command;
import com.sixppl.cmd.UserLoginCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.TestUserLoginHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class TestUserLoginCommand {

	@Test
	public void test() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
		Connection conn = Application.getSharedInstance().getDAOSupport().getConnection();

		// The actual test
		Command userCmd = new UserLoginCommand();
		HttpServletRequest request = new TestUserLoginHttpServletRequest();
		try {
			userCmd.execute(request, null);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
		
		Application.getSharedInstance().destroy();
	}

}

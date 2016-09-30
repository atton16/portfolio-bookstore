package com.sixppl.tests.cmd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.sixppl.cmd.Command;
import com.sixppl.cmd.SearchCommand;
import com.sixppl.main.Application;
import com.sixppl.tests.support.GenericTestHttpServletRequest;

public class TestSearchCommand {
	@Before
	public void init() {
		Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
		System.out.println("Test initialized");
	}

	@Test
	public void test() {
		System.out.println("Start Empty Search Test");
		Map<String, String[]> inputMap = new HashMap<String, String[]>();
		Command search = new SearchCommand();
		HttpServletRequest request = new GenericTestHttpServletRequest(inputMap);
		try {
			search.execute(request, null);
		} catch (ServletException e) {
			fail("Internal code or runtime environment error");
		} catch (IOException e) {
			fail("Internal code or runtime environment error");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(request.getAttribute("start"));
		System.out.println(request.getAttribute("end"));
		System.out.println(request.getAttribute("total"));
		System.out.println(request.getAttribute("prevParams"));
		System.out.println(request.getAttribute("nextParams"));
		fail("End Test");
	}

}

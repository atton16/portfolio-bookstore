package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.DummyDAO;
import com.sixppl.main.Application;

public class DummyCommand implements Command {
	private DummyDAO dummyDao;
	public DummyCommand() {
		dummyDao = Application.getSharedInstance().getDAOFactory().getDummyDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = dummyDao.getPasswordHash("dummy");
		request.setAttribute("password", password);
	}

}

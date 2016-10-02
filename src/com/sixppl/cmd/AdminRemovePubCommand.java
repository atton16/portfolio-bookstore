package com.sixppl.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.dao.EntityDAO;
import com.sixppl.dao.GraphDAO;
import com.sixppl.main.Application;

public class AdminRemovePubCommand implements Command{
	private AdminPubDAO adminPubDao;
	private GraphDAO graphDao;
	private EntityDAO entityDao;
	
	public AdminRemovePubCommand() {
		adminPubDao = Application.getSharedInstance().getDAOFactory().getAdminPubDAO();
		graphDao = Application.getSharedInstance().getDAOFactory().getGraphDAO(); 
		entityDao = Application.getSharedInstance().getDAOFactory().getEntityDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean success=false;
		Integer pubID;
		try {
			pubID = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			request.setAttribute("error", true);
			return;
		}
		
		//Check graph
		ArrayList<String> duplicatedNodes = graphDao.findDuplicatedNodeTo(pubID);
		duplicatedNodes.addAll(graphDao.findDuplicatedNodeFrom(pubID));
		for(int i = 0; i < duplicatedNodes.size(); i+=2){
			try {
				entityDao.updatePubIDByEntityID(Integer.valueOf(duplicatedNodes.get(i+1)), duplicatedNodes.get(i));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Remove from Listing
		success = adminPubDao.removePub(pubID);
		request.setAttribute("error", !success);
	}
}

package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserDAO;
import com.sixppl.dao.support.AdminUserDAOImpl;
import com.sixppl.dto.UserDTO;

public class AdminUserNextPrevPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if(!request.getParameter("nextpage").equals(null) && !request.getParameter("prevpage").equals(null)){
			Integer currentpage = Integer.parseInt(request.getParameter("nextpage"));
			if((Integer.parseInt(request.getParameter("total"))/10)<=currentpage+2){
				Integer nextpage=null;
			}
			Integer nextpage=currentpage+1;
			Integer prevpage=currentpage-1;
			
		}
		else if(!request.getParameter("nextpage").equals(null) && request.getParameter("prevpage").equals(null)){
			Integer currentpage = Integer.parseInt(request.getParameter("nextpage"));
			Integer nextpage = currentpage+1;
			Integer prevpage=1;
		}
		else if(request.getParameter("nextpage").equals(null) && !request.getParameter("prevpage").equals(null)){
			Integer currentpage = Integer.parseInt(request.getParameter(""));
			Integer nextpage = null
			Integer prevpage=1;
		}*/
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		List<UserDTO> templist= new ArrayList<UserDTO>();
		AdminUserDAO temp = new AdminUserDAOImpl();
		
		if(type.equals("Nickname")){
			templist=temp.findByNickname(keyword);
		}
		else if(type.equals("Firstname")){
			templist=temp.findByFirstname(keyword);
		}
		else if(type.equals("Lastname")){
			templist=temp.findByLastname(keyword);
		}
		else if(type.equals("Email")){
			templist=temp.findByEmail(keyword);
		}
		else if(type.equals("All Customers")){
			templist=temp.findAllCustomers();
		}
		else if(type.equals("All Sellers")){
			templist=temp.findAllSellers();
		}
		
		
		if(request.getParameterMap().containsKey("nextpage")){
			Integer currentpage=Integer.parseInt("nextpage");
			request.setAttribute("items", templist.subList((currentpage-1)*10, (currentpage*10-1)));
			if((Integer.parseInt(request.getParameter("total"))/10)<=currentpage){
				String nextParams=null;
				String prevParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&prevpage=" + Integer.toString((currentpage-1));
			}
			else if((Integer.parseInt(request.getParameter("total"))/10)>currentpage){
				String nextParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&nextpage=" + Integer.toString((currentpage+1));
				String prevParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&prevpage=" + Integer.toString((currentpage-1));
			}
		}
		else if(request.getParameterMap().containsKey("prevpage")){
			Integer currentpage=Integer.parseInt("prevpage");
			request.setAttribute("items", templist.subList((currentpage-1)*10, (currentpage*10-1)));
			if(currentpage==1){
				String prevParams=null;
				String nextParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&nextpage=" + Integer.toString((currentpage+1));
			}
			else if((Integer.parseInt(request.getParameter("total"))/10)>currentpage){
				String nextParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&nextpage=" + Integer.toString((currentpage+1));
				String prevParams="?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&prevpage=" + Integer.toString((currentpage-1));
			}
		}
	}

}

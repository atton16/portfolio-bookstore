package com.sixppl.dao.support;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.main.Application;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminPubDAOImpl implements AdminPubDAO{
	@SuppressWarnings("unchecked")
	public JSONArray findPub(Integer pubID){
		Connection con = null;

		UserDAO userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		JSONArray mainarray= new JSONArray();

		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement(
					"SELECT * FROM Listing WHERE PubID=?");
			stmt.setInt(1, pubID);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				JSONObject temp=new JSONObject();
				String PubID = Integer.toString(rs.getInt("PubID"));
				String Title=rs.getString("Title");
				String AuthorsString = rs.getString("Authors");
				String EditorsString = rs.getString("Editors");
				String Picture = rs.getString("Picture");
				Integer Price = rs.getInt("Price");
				String Nickname = userDao.findUserByUserID(rs.getInt("SellerID")).getNickname();
				Timestamp timestamp = rs.getTimestamp("Timestamp");

				List<String> authors = new ArrayList<String>();
				List<String> editors = new ArrayList<String>();
				String[] authors_buf = AuthorsString.split(",");
				for(String author:authors_buf){
					if(author.trim().length() > 0)
						authors.add(author.trim());
				}
				String[] editors_buf = EditorsString.split(",");
				for(String editor:editors_buf){
					if(editor.trim().length() > 0)
						editors.add(editor.trim());
				}
				temp.put("id", PubID);
				temp.put("title", Title);
				temp.put("authors", authors);
				temp.put("editors", editors);
				temp.put("picurl", Picture);
				temp.put("price", Price);
				temp.put("seller", Nickname);
				temp.put("listed", new SimpleDateFormat("dd/MM/yy").format(timestamp));
				mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		}
		return mainarray;
	}
	public Boolean removePub(Integer pubID){
		Connection con = null;
		Boolean result = false;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Listing WHERE PubID=?");
			stmt.setLong(1, pubID);
			result = stmt.execute();
			result = true;

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return result;
	}
}

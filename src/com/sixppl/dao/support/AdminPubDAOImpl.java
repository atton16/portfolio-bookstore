package com.sixppl.dao.support;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.main.Application;

import java.sql.*;

public class AdminPubDAOImpl implements AdminPubDAO{
	public JSONArray findPub(Integer pubID){
		Connection con = null;
		
		JSONArray mainarray= new JSONArray();
		
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
  
			PreparedStatement stmt = con.prepareStatement(
		       "SELECT * FROM Listing WHERE PubID=?");
		     stmt.setInt(1, pubID);
		     ResultSet rs= stmt.executeQuery();
		     while(rs.next()){
		    	 JSONObject temp=new JSONObject();
		    	 String Title=rs.getString("Title");
		    	 String Authors = rs.getString("Authors");
		    	 String Price = Integer.toString(rs.getInt("Price"));
		    	 String PubID = Integer.toString(rs.getInt("PubID"));
		    	 String SellerID = Integer.toString(rs.getInt("SellerID"));
		    	 temp.put("Title",Title);
		    	 temp.put("Authors", Authors);
		    	 temp.put("Price", Price);
		    	 temp.put("PubID", PubID);
		    	 temp.put("SellerID",SellerID);
		    	 mainarray.add(temp);
		     }
		     
			
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } finally {
		      if (con != null) {
		         try {
		           con.close();
		         } catch (SQLException e1) {
		           e1.printStackTrace();
		         }
		      }
		   }
		
		/*
		JSONObject temp= new JSONObject();
		temp.put("Title", "MyTitle");
		temp.put("Author", "Author");
		temp.put("PubID",0001);
		mainarray.add(temp);*/
		return mainarray;
	}
	public Boolean removePub(Integer pubID, Integer sellerID){
		Connection con=null;
		Boolean result=false;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
		     PreparedStatement stmt = con.prepareStatement(
		       "DELETE FROM Listing WHERE PubID=? && SellerID=?");
		     stmt.setInt(1, pubID);
		     stmt.setInt(2, sellerID);
		     result= stmt.execute();
		     
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } finally {
		      if (con != null) {
		         try {
		           con.close();
		         } catch (SQLException e1) {
		           e1.printStackTrace();
		         }
		      }
		   }
		return result;
	}
}

package com.sixppl.dao.support;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sixppl.dao.*;
import com.sixppl.dto.AdminAnalyticsDTO;
import com.sixppl.main.Application;
public class AdminAnalyticsDAOImpl implements AdminAnalyticsDAO{

	@Override
	public List<AdminAnalyticsDTO> getPageHits() {
		List<AdminAnalyticsDTO> phlist = null;
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("SELECT * FROM PageHits ORDER BY Hits");
		     ResultSet rs=stmt.executeQuery();
		     phlist=new ArrayList<AdminAnalyticsDTO>();
		     
		     while(rs.next()){
		    	 String uri = rs.getString("Page");
		    	 Integer pagehitsnumber=rs.getInt("Hits");
		    	 AdminAnalyticsDTO temp= new AdminAnalyticsDTO();
		    	 temp.setURI(uri);
		    	 temp.setPageHits(pagehitsnumber);
		    	 phlist.add(temp);
		     }
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		
		
		return phlist;
	}

	@Override
	public List<AdminAnalyticsDTO> getTopViewTitles() {
		List<AdminAnalyticsDTO> phlist = null;
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("SELECT * FROM MostViewed ORDER BY Count DESC LIMIT 10");
		     ResultSet rs=stmt.executeQuery();
		     phlist=new ArrayList<AdminAnalyticsDTO>();
		     
		     while(rs.next()){
		    	 String title = rs.getString("Title");
		    	 Integer viewcount=rs.getInt("Count");
		    	 AdminAnalyticsDTO temp= new AdminAnalyticsDTO();
		    	 temp.setMostViewedPubTitle(title);
		    	 temp.setMostViewCount(viewcount);
		    	 phlist.add(temp);
		     }
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		
		
		return phlist;
	}

	@Override
	public List<AdminAnalyticsDTO> getTopAddedCart() {
		List<AdminAnalyticsDTO> phlist = null;
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("SELECT * FROM MostAddedToCart ORDER BY Count DESC LIMIT 10");
		     ResultSet rs=stmt.executeQuery();
		     phlist=new ArrayList<AdminAnalyticsDTO>();
		     
		     while(rs.next()){
		    	 String title = rs.getString("Title");
		    	 Integer viewcount=rs.getInt("Count");
		    	 AdminAnalyticsDTO temp= new AdminAnalyticsDTO();
		    	 temp.setMostAddedToCartPubTitle(title);
		    	 temp.setMostAddedCount(viewcount);
		    	 phlist.add(temp);
		     }
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		
		
		return phlist;
	}

	@Override
	public Boolean updatePageHits(String URI) {
		Connection con = null;
		//String URImod = URI.replaceAll("/", "");
		Boolean status = false;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("UPDATE PageHits SET Hits=Hits+1 WHERE Page=?");
			 stmt.setString(1, URI);
		     stmt.execute();
		     status=true;
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		return status;
		
	}

	@Override
	public Boolean updateTopViewTitles(String Title) {
		Connection con = null;
		//String URImod = URI.replaceAll("/", "");
		Boolean status = false;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("UPDATE MostViewed SET Count=Count+1 WHERE Title=?");
			 stmt.setString(1, Title);
		     stmt.execute();
		     status=true;
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		return status;
	}

	@Override
	public Boolean updateTopAddedCart(String Title) {
		Connection con = null;
		//String URImod = URI.replaceAll("/", "");
		Boolean status = false;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			 PreparedStatement stmt = con.prepareStatement("UPDATE MostAddedToCart SET Count=Count+1 WHERE Title=?");
			 stmt.setString(1, Title);
		     stmt.execute();
		     status=true;
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		return status;
	}

	@Override
	public AdminAnalyticsDTO getUsersActive() {
		// TODO Auto-generated method stub
		return null;
	}

}

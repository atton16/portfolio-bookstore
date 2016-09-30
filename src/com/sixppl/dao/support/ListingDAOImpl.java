package com.sixppl.dao.support;

import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.sixppl.dao.ListingDAO;
import com.sixppl.main.Application;

//import sun.nio.cs.StandardCharsets;

import com.sixppl.dto.ListingDTO;

public class ListingDAOImpl implements ListingDAO {

	private Connection conn;
	
	public ListingDAOImpl(){
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}
	
	public int getTotal(){
		int total = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT count(*) AS Count FROM Listing;";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			total = rs.getInt("Count");
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		return total;
	}
	
	public ArrayList<ListingDTO> emptySearch(){
		
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing;";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getInt("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getLong("timestamp"));
				
				results.add(pub);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		
		return results;
	}

	@Override
	public ArrayList<ListingDTO> Search(ListingDTO pubKey) {
		// TODO Auto-generated method stub
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing;";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getInt("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getLong("timestamp"));
				if(pub.similar(pubKey)){
					results.add(pub);
				}
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		
		return results;
	}
	
	public boolean addListing(ListingDTO pubSell){
		boolean pass = false;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO `Listing` (`Title`,`Authors`,`Editors`,`Type`,`Year`,`Venue`,`Picture`,`Price`,`Status`) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pubSell.title);
			String authors = "";
			for(String author: pubSell.authors){
				authors += author;
				authors += ",";
			}
			authors = authors.substring(0,authors.lastIndexOf(","));
			stmt.setString(2, authors);
			String editors = "";
			for(String editor: pubSell.editors){
				editors += editor;
				editors += ",";
			}
			editors = editors.substring(0,editors.lastIndexOf(","));
			stmt.setString(3, editors);
			
			stmt.setString(4, pubSell.type);
			stmt.setInt(5, pubSell.year);
			stmt.setString(6, pubSell.venue);
			Scanner s = new Scanner(pubSell.picture).useDelimiter("\\A");
			String picString = s.hasNext() ? s.next() : "";
			stmt.setString(7, picString);
			stmt.setInt(8, pubSell.price);
			stmt.setBoolean(9, true);
			stmt.executeUpdate();
			pass = true;

			//STEP 6: Clean-up environment
			
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			pass = false;
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			pass = false;
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		return pass;
	}
	public void setList(int pubID){
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE Listing SET Status = ? WHERE PubID = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setInt(2, pubID);
			stmt.executeUpdate();

			
			//STEP 6: Clean-up environment
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
	}
	public void setUnlist(int pubID){
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE Listing SET Status = ? WHERE PubID = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, false);
			stmt.setInt(2, pubID);
			stmt.executeUpdate();

			
			//STEP 6: Clean-up environment
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
	}

	@Override
	public Integer getListingCount() {

		Integer total = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) AS total FROM Listing";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				total = rs.getInt("total");
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return total;
	}

	@Override
	public List<Integer> getYearsAscending() {
		
		List<Integer> years = new ArrayList<Integer>();
		PreparedStatement stmt = null;
		String sql = "SELECT distinct Year FROM Listing ORDER BY Year ASC";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Integer year = rs.getInt("Year");
				years.add(year);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}// nothing we can do
		}//end try

		return years;
	}

	@Override
	public List<Integer> getYearsDescending() {
		
		List<Integer> years = new ArrayList<Integer>();
		PreparedStatement stmt = null;
		String sql = "SELECT distinct Year FROM Listing ORDER BY Year DESC";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Integer year = rs.getInt("Year");
				years.add(year);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}// nothing we can do
		}//end try

		return years;
	}
}

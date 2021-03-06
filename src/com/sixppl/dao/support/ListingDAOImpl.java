package com.sixppl.dao.support;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.main.Application;

import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.SessionDTO;

public class ListingDAOImpl implements ListingDAO {

	private Connection conn;
	
	public ListingDAOImpl(){
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}
	
	public int getTotal(){
		int total = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) AS Total FROM Listing";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				total = rs.getInt("Total");
			}
			System.out.println("Total Pub : " + total);
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
	
	public int getLastPubID(){
		int pubID = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT PubID FROM Listing ORDER BY PubID DESC LIMIT 1";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				pubID = rs.getInt("PubID");
			}
			System.out.println("Last Pub ID : " + pubID);
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
		return pubID;
	}
	
	public ArrayList<ListingDTO> emptySearch(String sessionId){
		
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		CartDAO cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(sessionId);
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing LEFT JOIN User ON Listing.SellerID=User.UserID";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getFloat("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getTimestamp("timestamp"));
				pub.setSellerNickname(rs.getString("Nickname"));
				pub.setInCart(cartDao.isInCart(rs.getInt("PubID"), userID));
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
	public ArrayList<ListingDTO> Search(ListingDTO pubKey, String sessionId) {
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		CartDAO cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(sessionId);
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing LEFT JOIN User ON Listing.SellerID=User.UserID";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getFloat("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getTimestamp("timestamp"));
				pub.setSellerNickname(rs.getString("Nickname"));
				pub.setInCart(cartDao.isInCart(rs.getInt("PubID"), userID));
				if(pub.similar(pubKey) && pub.status == true){
					System.out.println("found");
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
	
	@Override
	public ArrayList<ListingDTO> searchAll(ListingDTO pubKey, String sessionId) {
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		CartDAO cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(sessionId);
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing LEFT JOIN User ON Listing.SellerID=User.UserID";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getFloat("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getTimestamp("timestamp"));
				pub.setSellerNickname(rs.getString("Nickname"));
				pub.setInCart(cartDao.isInCart(rs.getInt("PubID"), userID));
				if(pub.similar(pubKey)){
					System.out.println("found");
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
		String sql = "INSERT INTO `Listing` (`Title`,`Authors`,`Editors`,`Type`,`Year`,`Venue`,`SellerID`,`Picture`,`Price`,`Status`) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
			stmt.setInt(7, pubSell.sellerID);
			@SuppressWarnings("resource")
			Scanner s = new Scanner(pubSell.picture).useDelimiter("\\A");
			String picString = s.hasNext() ? s.next() : "";
			stmt.setString(8, picString);
			stmt.setFloat(9, pubSell.price);
			stmt.setBoolean(10, true);
			stmt.executeUpdate();
			pass = true;

			//STEP 6: Clean-up environment
			
			s.close();
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
	public Integer getListingCount(Integer userId) {

		Integer total = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) AS total FROM Listing WHERE SellerID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
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

	@Override
	public List<ListingDTO> getRandomPub(String sessionId) {
		ArrayList<ListingDTO> results = new ArrayList<ListingDTO>();
		UserDAO userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		CartDAO cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(sessionId);
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Listing AS r1 JOIN (SELECT CEIL(RAND() * (SELECT MAX(PubID) FROM Listing)) AS PubID) AS r2 WHERE r1.PubID >= r2.PubID AND r1.Status = 1 ORDER BY r1.PubID ASC LIMIT 1";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getFloat("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getTimestamp("timestamp"));
				pub.setSellerNickname(userDao.findUserByUserID(rs.getInt("SellerID")).getNickname());
				pub.setInCart(cartDao.isInCart(rs.getInt("PubID"), userID));
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
	public ListingDTO getByPubID(int pubID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListingDTO pub = null;
		UserDAO userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		String sql = "SELECT * FROM Listing WHERE `PubID` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pubID);
			rs = stmt.executeQuery();
			while(rs.next()){
				pub = new ListingDTO();
				pub.setAttributes(
						rs.getInt("PubID"),
						rs.getString("Title"),
						rs.getString("Authors"),
						rs.getString("Editors"),
						rs.getString("Type"), 
						rs.getInt("Year"),
						rs.getString("Venue"),
						rs.getInt("SellerID"),
						rs.getString("Picture"),
						rs.getFloat("Price"),
						rs.getBoolean("Status"), 
						rs.getInt("SoldCount"),
						rs.getTimestamp("timestamp")
						);
				pub.setSellerNickname(userDao.findUserByUserID(rs.getInt("SellerID")).getNickname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {}
		
		return pub;
	}

	@Override
	public long getMaxPubID() {
		long result = 0;
		String sql = "SELECT MAX(PubID) AS CurrentPubID FROM Listing";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong("CurrentPubID");
			}
		    rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public Integer getListingCountExceptPaused() {

		Integer total = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) AS total FROM Listing WHERE `Status` = 1";
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
}

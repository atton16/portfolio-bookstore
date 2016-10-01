package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.ListingStatisticsDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.ListingStatisticsDTO;
import com.sixppl.main.Application;

public class ListingStatisticsDAOImpl implements ListingStatisticsDAO {
	
	private Connection conn;
	private ListingDAO listingDao;

	public ListingStatisticsDAOImpl() {
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
		listingDao = new ListingDAOImpl();
	}
	
	@Override
	public List<ListingDTO> getMostAddedToCart(Integer limit) {
		List<ListingDTO> pubs = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListingStatisticsDTO listingStat = null;
		String sql = "SELECT * FROM `ListingStatistics` WHERE `AddedToCart` > '0' ORDER BY `AddedToCart` DESC LIMIT ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			while(rs.next()){
				listingStat = new ListingStatisticsDTO();
				listingStat.setPubID(rs.getInt("PubID"));
				listingStat.setAddedToCart(rs.getInt("AddedToCart"));
				listingStat.setViewed(rs.getInt("Viewed"));
				ListingDTO pub = listingDao.getByPubID(listingStat.getPubID());
				pub.setViewed(listingStat.getViewed());
				pub.setAddedToCart(listingStat.getAddedToCart());
				pubs.add(pub);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (Exception e) {}
		return pubs;
	}

	@Override
	public List<ListingDTO> getMostViewed(Integer limit) {
		List<ListingDTO> pubs = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListingStatisticsDTO listingStat = null;
		String sql = "SELECT * FROM `ListingStatistics` WHERE `Viewed` > '0' ORDER BY `Viewed` DESC LIMIT ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			while(rs.next()){
				listingStat = new ListingStatisticsDTO();
				listingStat.setPubID(rs.getInt("PubID"));
				listingStat.setAddedToCart(rs.getInt("AddedToCart"));
				listingStat.setViewed(rs.getInt("Viewed"));
				ListingDTO pub = listingDao.getByPubID(listingStat.getPubID());
				pub.setViewed(listingStat.getViewed());
				pub.setAddedToCart(listingStat.getAddedToCart());
				pubs.add(pub);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (Exception e) {}
		return pubs;
	}

	@SuppressWarnings("resource")
	@Override
	public void incrementMostAddedToCart(Integer pubID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListingStatisticsDTO listingStat = null;
		String sql = "SELECT * FROM `ListingStatistics` WHERE `PubID` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pubID);
			rs = stmt.executeQuery();
			while(rs.next()){
				listingStat = new ListingStatisticsDTO();
				listingStat.setPubID(rs.getInt("PubID"));
				listingStat.setAddedToCart(rs.getInt("AddedToCart"));
				listingStat.setViewed(rs.getInt("Viewed"));
			}
			rs.close();
			stmt.close();
			if(listingStat == null) {
				sql = "INSERT INTO `ListingStatistics`(`PubID`, `AddedToCart`, `Viewed`) VALUES (?,1,0)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pubID);
				stmt.execute();
				stmt.close();
			} else {
				sql = "UPDATE `ListingStatistics` SET `AddedToCart`=? WHERE `PubID`=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, listingStat.getAddedToCart()+1);
				stmt.setInt(2, pubID);
				stmt.execute();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (Exception e) {}
	}

	@SuppressWarnings("resource")
	@Override
	public void incrementMostViewed(Integer pubID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListingStatisticsDTO listingStat = null;
		String sql = "SELECT * FROM `ListingStatistics` WHERE `PubID` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pubID);
			rs = stmt.executeQuery();
			while(rs.next()){
				listingStat = new ListingStatisticsDTO();
				listingStat.setPubID(rs.getInt("PubID"));
				listingStat.setAddedToCart(rs.getInt("AddedToCart"));
				listingStat.setViewed(rs.getInt("Viewed"));
			}
			rs.close();
			stmt.close();
			if(listingStat == null) {
				sql = "INSERT INTO `ListingStatistics`(`PubID`, `AddedToCart`, `Viewed`) VALUES (?,0,1)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pubID);
				stmt.execute();
				stmt.close();
			} else {
				sql = "UPDATE `ListingStatistics` SET `Viewed`=? WHERE `PubID`=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, listingStat.getViewed()+1);
				stmt.setInt(2, pubID);
				stmt.execute();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (Exception e) {}
	}

}

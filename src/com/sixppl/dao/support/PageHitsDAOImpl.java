package com.sixppl.dao.support;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.sixppl.dao.*;
import com.sixppl.dto.PageHitsDTO;
import com.sixppl.main.Application;
public class PageHitsDAOImpl implements PageHitsDAO{

	private Connection conn;

	public PageHitsDAOImpl() {
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}

	@SuppressWarnings("resource")
	@Override
	public void incrementHitCount(String Title) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PageHitsDTO pageHits = null;
		String sql = "SELECT * FROM `PageHits` WHERE `Page` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, Title);
			rs = stmt.executeQuery();
			while(rs.next()){
				pageHits = new PageHitsDTO();
				pageHits.setTitle(rs.getString("Page"));
				pageHits.setHitCount(rs.getInt("Hits"));
			}
			rs.close();
			stmt.close();
			if(pageHits == null) {
				sql = "INSERT INTO `PageHits`(`Page`, `Hits`) VALUES (?,1)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, Title);
				stmt.execute();
				stmt.close();
			} else {
				sql = "UPDATE `PageHits` SET `Hits`=? WHERE `Page`=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pageHits.getHitCount()+1);
				stmt.setString(2, Title);
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

	@Override
	public List<PageHitsDTO> getPageHits() {
		List<PageHitsDTO> pageHitsList = new ArrayList<PageHitsDTO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PageHits";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				PageHitsDTO pageHits = new PageHitsDTO();
				pageHits.setTitle(rs.getString("Page"));
				pageHits.setHitCount(rs.getInt("Hits"));
				pageHitsList.add(pageHits);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (Exception e) {}

		return pageHitsList;
	}

}

package com.sixppl.dao.support;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sixppl.dao.TransactionDAO;
import com.sixppl.dto.TransactionDTO;
import com.sixppl.main.Application;

public class TransactionDAOImpl implements TransactionDAO {
	private Connection conn;
	public TransactionDAOImpl() {
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}

	@Override
	public List<TransactionDTO> getByBuyerID(int buyerID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
		String sql = "SELECT * FROM Transaction WHERE BuyerID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, buyerID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				TransactionDTO transaction = new TransactionDTO();
				transaction.setBuyerID(rs.getInt("BuyerID"));
				transaction.setSellerID(rs.getInt("SellerID"));
				transaction.setPubID(rs.getInt("PubID"));
				transaction.setOrderNumber(rs.getInt("OrderNumber"));
				transaction.setPurchaseTime(rs.getTimestamp("PurchaseTime"));
				transaction.setSellingPrice(rs.getInt("SellingPrice"));
				transactions.add(transaction);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {}
		return transactions;
	}

}

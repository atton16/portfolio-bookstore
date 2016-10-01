package com.sixppl.dao;

import java.util.List;

import com.sixppl.dto.TransactionDTO;

public interface TransactionDAO {
	List<TransactionDTO> getByBuyerID(int buyerID);
}

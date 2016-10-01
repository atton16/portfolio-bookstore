package com.sixppl.dto;

import java.sql.Timestamp;

public class TransactionDTO {
	private Integer buyerID;
	private Integer sellerID;
	private Integer pubID;
	private Integer orderNumber;
	private Timestamp purchaseTime;
	private Integer sellingPrice;
	
	public TransactionDTO() {
		buyerID = -1;
		sellerID = -1;
		pubID = -1;
		orderNumber = -1;
		purchaseTime = null;
		sellingPrice = -1;
	}
	
	public void setBuyerID(Integer buyerID) {
		this.buyerID = buyerID;
	}
	
	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public Integer getBuyerID() {
		return this.buyerID;
	}
	
	public Integer getSellerID() {
		return this.sellerID;
	}
	
	public Integer getPubID() {
		return this.pubID;
	}
	
	public Integer getOrderNo() {
		return this.orderNumber;
	}
	
	public Timestamp getPurchaseTime() {
		return this.purchaseTime;
	}
	
	public Integer getSellingPrice() {
		return this.sellingPrice;
	}
	
}

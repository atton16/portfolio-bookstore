package com.sixppl.bean;

import java.util.ArrayList;

import com.sixppl.dto.ListingDTO;

public class CartBean {
	
	private ArrayList<ListingDTO> pubList;
	
	public CartBean(){
		this.pubList = new ArrayList<ListingDTO>();
	}
	
	public void setCartList(ArrayList<ListingDTO> pubList){
		this.pubList = pubList;
	}
	
	public ArrayList<ListingDTO> getCartList(){
		return pubList;
	}

}

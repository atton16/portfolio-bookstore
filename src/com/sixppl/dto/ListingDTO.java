package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Date;

public class ListingDTO {

	int pubID;
	String title;
	ArrayList<String> authors;
	ArrayList<String> editors;
	String type;
	int year;
	String venue;
	int sellerID;
	String picture;
	int price;
	boolean status;
	int soldCount;
	long timestamp;
	Date date;
	
	public ListingDTO(){
		this.pubID = 0;
		this.title = "";
		this.authors = new ArrayList<String>();
		this.editors = new ArrayList<String>();
		this.type = "";
		this.year = 0;
		this.venue = "";
		this.sellerID = 0;
		this.picture = "";
		this.price = 0;
		this.status = false;
		this.soldCount = 0;
		this.timestamp = 0;
		this.date = new Date(timestamp);
	}
	
	public void setAttributes(int pubID,String title,String authors,String editors,String type,
			int year,String venue,int sellerID,String picture,int price,boolean status,
			int soldCount,long timestamp){
		this.pubID = pubID;
		this.title = title;
		String[] authors_buf = authors.split(",");
		for(String author:authors_buf){
			this.authors.add(author.trim());
		}
		String[] editors_buf = editors.split(",");
		for(String editor:editors_buf){
			this.editors.add(editor.trim());
		}
		this.type = type;
		this.soldCount = soldCount;
		this.year = year;
		this.venue = venue;
		this.sellerID = sellerID;
		this.picture = picture;
		this.price = price;
		this.status = status;
		this.timestamp = timestamp;
		this.date = new Date(timestamp);
	}
	public int getPubID(){
		return pubID;
	}
	public String getTitle(){
		return title;
	}
	public ArrayList<String> getAuthors(){
		return authors;
	}
	public ArrayList<String> getEditors(){
		return editors;
	}
	public String getType(){
		return type;
	}
	public int getSoldCount(){
		return soldCount;
	}
	public int getYear(){
		return year;
	}
	public String getVenue(){
		return venue;
	}
	public String getPicture(){
		return picture;
	}
	public int getSellerID(){
		return sellerID;
	}
	public int getPrice(){
		return price;
	}
	public boolean getStatus(){
		return status;
	}
	public long getTimestamp(){
		return timestamp;
	}
	public Date getDate(){
		return date;
	}
	
}

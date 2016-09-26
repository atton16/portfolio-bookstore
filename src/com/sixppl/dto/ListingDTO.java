package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Date;

public class ListingDTO {

	public int pubID;
	public String title;
	public ArrayList<String> authors;
	public ArrayList<String> editors;
	public ArrayList<String> writers;
	public String type;
	public int year;
	public int fromYear;
	public int toYear;
	public String venue;
	public int sellerID;
	public String picture;
	public int price;
	public boolean status;
	public int soldCount;
	public long timestamp;
	public Date date;
	
	public ListingDTO(){
		this.pubID = 0;
		this.title = "";
		this.authors = new ArrayList<String>();
		this.editors = new ArrayList<String>();
		this.writers = new ArrayList<String>();
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
		this.fromYear = 0;
		this.toYear = 9999;
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
		ArrayList<String> writer = this.authors;
		for(String editor: this.editors){
			writer.add(editor);
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
	public void setYearRange(int fromYear,int toYear){
		this.fromYear = fromYear;
		this.toYear = toYear;
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
	public ArrayList<String> getWriters(){
		return writers;
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
	
	public boolean similar(ListingDTO pubKey){
		
		if(!pubKey.title.isEmpty() && !this.title.toLowerCase().contains(pubKey.title.toLowerCase())){
			return false;
		}
		
		if(!pubKey.writers.isEmpty()){
			for(String keyWriter: pubKey.writers){
				boolean pass = false;
				for(String writer: this.writers){
					if(writer.toLowerCase().contains(keyWriter.toLowerCase())){
						pass = true;
						break;
					}
				}
				if(pass == false){
					return false;
				}
			}
		}
		
		if(!pubKey.type.isEmpty() && !this.type.toLowerCase().contains(pubKey.type.toLowerCase())){
			return false;
		}
		
		if(this.year >= pubKey.toYear || this.year <= pubKey.fromYear){
			return false;
		}
		
		if(!pubKey.venue.isEmpty() && !this.venue.toLowerCase().contains(pubKey.venue.toLowerCase())){
			return false;
		}
		return true;
	}
	
}

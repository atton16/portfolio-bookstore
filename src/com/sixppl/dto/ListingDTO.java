package com.sixppl.dto;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Part;
import javax.swing.JSpinner.DateEditor;

import org.apache.commons.io.IOUtils;

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
	public Timestamp timestamp;
	public Date date;
	public String sellerNickname;
	
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
		this.timestamp = null;
		this.date = timestamp;
		this.fromYear = 0;
		this.toYear = 9999;
		this.sellerNickname = "";
	}
	
	public void setAttributes(int pubID,String title,String authors,String editors,String type,
			int year,String venue,int sellerID,String picture,int price,boolean status,
			int soldCount,Timestamp timestamp) throws Exception{
		this.pubID = pubID;
		this.title = title;
		String[] authors_buf = authors.split(",");
		for(String author:authors_buf){
			if(author.trim().length() > 0)
				this.authors.add(author.trim());
		}
		String[] editors_buf = editors.split(",");
		for(String editor:editors_buf){
			if(editor.trim().length() > 0)
				this.editors.add(editor.trim());
		}
		writers = this.authors;
		for(String editor: this.editors){
			writers.add(editor);
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
		this.date = timestamp;
	}
	
	public void setSellerNickname(String name) {
		this.sellerNickname = name;
	}
	
	public void setPubID(int id){
		this.pubID = id;
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
	public Timestamp getTimestamp(){
		return timestamp;
	}
	public Date getDate(){
		return date;
	}
	public String getTimestampString(){
		return new SimpleDateFormat("dd/MM/yy").format(timestamp);
	}
	
	public boolean similar(ListingDTO pubKey){
		
		if(!pubKey.title.isEmpty() && !this.title.toLowerCase().contains(pubKey.title.toLowerCase())){
			System.out.println("title no pass");
			return false;
		}
		
		if(!pubKey.writers.isEmpty()){
			for(String keyWriter: pubKey.writers){
				System.out.println("keyWriter :" + keyWriter);
				boolean pass = false;
				for(String writer: this.writers){
					System.out.println("Writer :" + writer);
					if(writer.toLowerCase().contains(keyWriter.toLowerCase())){
						System.out.println("writer one pass");
						pass = true;
						break;
					}
				}
				if(pass == false){
					System.out.println("writer no pass");
					return false;
				}
			}
		}
		
		if(!pubKey.type.isEmpty() && !this.type.toLowerCase().contains(pubKey.type.toLowerCase())){
			System.out.println("type no pass");
			return false;
		}
		
		System.out.println("Year : " + this.year);
		System.out.println("from : " + pubKey.fromYear);
		System.out.println("to : " + pubKey.toYear);
		if(this.year > pubKey.toYear || this.year < pubKey.fromYear){
			System.out.println("year no pass");
			return false;
		}
		
		if(!pubKey.venue.isEmpty() && !this.venue.toLowerCase().contains(pubKey.venue.toLowerCase())){
			System.out.println("venue no pass");
			return false;
		}
		return true;
	}
	
	public String getSellerNickname() {
		return this.sellerNickname;
	}
	
}

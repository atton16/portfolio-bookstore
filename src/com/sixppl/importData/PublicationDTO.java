package com.sixppl.importData;

import java.util.ArrayList;

public class PublicationDTO {

	private String key;
	private String mdate;
	private ArrayList<String> author;
	private ArrayList<String> editor;
	private String title;
	private String booktitle;
	private String pages;
	private String year;
	private String address;
	private String journal;
	private String volume;
	private String number;
	private String month;
	private String url;
	private String ee;
	private String cdrom;
	private String cite;
	private String publisher;
	private String note;
	private String crossref;
	private String isbn;	
	private String series;
	private String school;
	private String chapter;
	private String type;
	
	public PublicationDTO() {
		this.key = null;
		this.mdate = null;
		this.author = new ArrayList<String>();
		this.editor = new ArrayList<String>();
		this.title = null;
		this.booktitle = null;
		this.pages = null;
		this.year = null;
		this.address = null;
		this.journal = null;
		this.volume = null;
		this.number = null;
		this.month = null;
		this.url = null;
		this.ee = null;
		this.cdrom = null;
		this.cite = null;
		this.publisher = null;
		this.note = null;
		this.crossref = null;
		this.isbn = null;
		this.series = null;
		this.school = null;
		this.chapter = null;
		this.type = null;
	}

	public PublicationDTO(String key, String mdate, ArrayList<String> author, ArrayList<String> editor, String title, 
			String booktitle, String pages, String year, String address, String journal, 
			String volume, String number, String month, String url, String ee, 
			String cdrom, String cite, String publisher, String note, String crossref, 
			String isbn, String series, String school, String chapter, String type) {
		super();
		this.key = key;
		this.mdate = mdate;
		this.author = author;
		this.editor = editor;
		this.title = title;
		this.booktitle = booktitle;
		this.pages = pages;
		this.year = year;
		this.address = address;
		this.journal = journal;
		this.volume = volume;
		this.number = number;
		this.month = month;
		this.url = url;
		this.ee = ee;
		this.cdrom = cdrom;
		this.cite = cite;
		this.publisher = publisher;
		this.note = note;
		this.crossref = crossref;
		this.isbn = isbn;
		this.series = series;
		this.school = school;
		this.chapter = chapter;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}

	public ArrayList<String> getEditor() {
		return editor;
	}

	public void setEditor(ArrayList<String> editor) {
		this.editor = editor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEe() {
		return ee;
	}

	public void setEe(String ee) {
		this.ee = ee;
	}

	public String getCdrom() {
		return cdrom;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCrossref() {
		return crossref;
	}

	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

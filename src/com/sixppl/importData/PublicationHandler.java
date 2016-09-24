package com.sixppl.importData;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PublicationHandler {

	private ArrayList<PublicationDTO> publicationList;
	
	public PublicationHandler() {
		// TODO Auto-generated constructor stub
		this.publicationList = new ArrayList<PublicationDTO>();
	}

	public ArrayList<PublicationDTO> getPublicationList() {
		return publicationList;
	}

	public void setPublicationList(ArrayList<PublicationDTO> publicationList) {
		this.publicationList = publicationList;
	}
	
	public PublicationDTO toPublication(Node node) {
		PublicationDTO publication = new PublicationDTO();
		Element element = (Element) node;
		String key;
		String mdate;
		ArrayList<String> author = new ArrayList<String>();
		ArrayList<String> editor = new ArrayList<String>();
		String title;
		String booktitle;
		String pages;
		String year;
		String address;
		String journal;
		String volume;
		String number;
		String month;
		String url;
		String ee;
		String cdrom;
		String cite;
		String publisher;
		String note;
		String crossref;
		String isbn;	
		String series;
		String school;
		String chapter;
		String type;
		
		type = element.getTagName();
		publication.setType(type);
		
		if (element.getAttribute("key") != null){
			key = element.getAttribute("key");
			publication.setKey(key);
		}
		if (element.getAttribute("mdate") != null){
			mdate = element.getAttribute("mdate");
			publication.setMdate(mdate);
		}
		if (element.getElementsByTagName("author").getLength() != 0){
			for(int i = 0; i < element.getElementsByTagName("author").getLength(); i++){
				author.add(element.getElementsByTagName("author").item(i).getTextContent().toString());
			}
			publication.setAuthor(author);
		}
		if (element.getElementsByTagName("editor").getLength() != 0){
			for(int i = 0; i < element.getElementsByTagName("editor").getLength(); i++){
				editor.add(element.getElementsByTagName("editor").item(i).getTextContent());
			}
			publication.setEditor(editor);
		}
		if (element.getElementsByTagName("title").getLength() != 0){
			title = element.getElementsByTagName("title").item(0).getTextContent();
			publication.setTitle(title);
		}
		if (element.getElementsByTagName("booktitle").getLength() != 0){
			booktitle = element.getElementsByTagName("booktitle").item(0).getTextContent();
			publication.setBooktitle(booktitle);
		}
		if (element.getElementsByTagName("pages").getLength() != 0){
			pages = element.getElementsByTagName("pages").item(0).getTextContent();
			publication.setPages(pages);
		}
		if (element.getElementsByTagName("year").getLength() != 0){
			year = element.getElementsByTagName("year").item(0).getTextContent();
			publication.setYear(year);
		}
		if (element.getElementsByTagName("address").getLength() != 0){
			address = element.getElementsByTagName("address").item(0).getTextContent();
			publication.setAddress(address);
		}
		if (element.getElementsByTagName("journal").getLength() != 0){
			journal = element.getElementsByTagName("journal").item(0).getTextContent();
			publication.setJournal(journal);
		}
		if (element.getElementsByTagName("volume").getLength() != 0){
			volume = element.getElementsByTagName("volume").item(0).getTextContent();
			publication.setVolume(volume);
		}
		if (element.getElementsByTagName("number").getLength() != 0){
			number = element.getElementsByTagName("number").item(0).getTextContent();
			publication.setNumber(number);
		}
		if (element.getElementsByTagName("month").getLength() != 0){
			month = element.getElementsByTagName("month").item(0).getTextContent();
			publication.setMonth(month);
		}
		if (element.getElementsByTagName("url").getLength() != 0){
			url = element.getElementsByTagName("url").item(0).getTextContent();
			publication.setUrl(url);
		}
		if (element.getElementsByTagName("ee").getLength() != 0){
			ee = element.getElementsByTagName("ee").item(0).getTextContent();
			publication.setEe(ee);
		}
		if (element.getElementsByTagName("cdrom").getLength() != 0){
			cdrom = element.getElementsByTagName("cdrom").item(0).getTextContent();
			publication.setCdrom(cdrom);
		}
		if (element.getElementsByTagName("cite").getLength() != 0){
			cite = element.getElementsByTagName("cite").item(0).getTextContent();
			publication.setCite(cite);
		}
		if (element.getElementsByTagName("publisher").getLength() != 0){
			publisher = element.getElementsByTagName("publisher").item(0).getTextContent();
			publication.setPublisher(publisher);
		}
		if (element.getElementsByTagName("note").getLength() != 0){
			note = element.getElementsByTagName("note").item(0).getTextContent();
			publication.setNote(note);
		}
		if (element.getElementsByTagName("crossref").getLength() != 0){
			crossref = element.getElementsByTagName("crossref").item(0).getTextContent();
			publication.setCrossref(crossref);
		}
		if (element.getElementsByTagName("isbn").getLength() != 0){
			isbn = element.getElementsByTagName("isbn").item(0).getTextContent();
			publication.setIsbn(isbn);
		}
		if (element.getElementsByTagName("series").getLength() != 0){
			series = element.getElementsByTagName("series").item(0).getTextContent();
			publication.setSeries(series);
		}
		if (element.getElementsByTagName("school").getLength() != 0){
			school = element.getElementsByTagName("school").item(0).getTextContent();
			publication.setSchool(school);
		}
		if (element.getElementsByTagName("chapter").getLength() != 0){
			chapter = element.getElementsByTagName("chapter").item(0).getTextContent();
			publication.setChapter(chapter);
		}
		
		return publication;
	}
	
	public PublicationDTO getPublication(String key, NodeList nodeList){
		PublicationDTO publication = new PublicationDTO();
		for (int i = 0; i < nodeList.getLength(); i++){
			Element element = (Element) nodeList.item(i);
			if (element.getAttribute("key") != null){
				String tempkey = element.getAttribute("key");
				if (key.equals(tempkey)){
					PublicationHandler handler = new PublicationHandler();
					publication = handler.toPublication(nodeList.item(i));
					break;
				}
			}
		}
		return publication;
	}

	public ArrayList<PublicationDTO> toPublicationList(NodeList nodeList){
		ArrayList<PublicationDTO> publicationlist = new ArrayList<PublicationDTO>();
		for (int i = 0; i < nodeList.getLength() ; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				publicationlist.add(toPublication(node));
			}
		}
		return publicationlist;
	}
}

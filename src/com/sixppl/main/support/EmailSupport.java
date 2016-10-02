package com.sixppl.main.support;

import java.util.List;
import java.util.Map;

import com.sixppl.dto.ListingDTO;

public class EmailSupport {
	public static String SenderEmail() {
		return "asst2unsw@gmail.com";
	}
	public static String RegistrationEmailSubject() {
		return "DBLP: Confirm Registration";
	}
	public static String ChangeEmailSubject() {
		return "DBLP: Email Cahange Confirmation";
	}
	public static String PurchaseToSellerSubject() {
		return "DBLP: DBLP: Purchase Notification";
	}
	
	public static String RegistrationEmailContent(String tokenLink) {
		return "Please click the link below to confirm your registration:<br/>"+tokenLink;
	}
	public static String ChangeEmailContent(String tokenLink) {
		return "Please click the link below to confirm your email:<br/>"+tokenLink;
	}
	public static String PurchaseToSellerContent(String sellerEmail, List<ListingDTO> items, Map<String,String> purchasedMap) {
		String itemList = "";
		for(ListingDTO item: items){
			if(purchasedMap.get(item.title).equalsIgnoreCase(sellerEmail)){
				itemList += item.title + "<br/>";
			}
		}
		
		return "Your item(s) have been purchased!<br/>"+
				"List of item(s):<br>"+
				itemList;
	}
}

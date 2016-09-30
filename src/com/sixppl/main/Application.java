package com.sixppl.main;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.taglibs.standard.lang.jstl.Logger;
import org.apache.tomcat.jni.Lock;

import com.sixppl.dao.DAOFactory;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.DAOSupport;
import com.sixppl.dto.ListingDTO;

/**
 * Singleton Application
 * @author atton16
 *
 */
public class Application {
	public static final String DEVELOPMENT_ENV = "DEVELOPMENT";
	public static final String PRODUCTION_ENV = "PRODUCTION";
	public static final String PRODUCTIONIP_ENV = "PRODUCTION_IP";
	private static final String ENV = PRODUCTIONIP_ENV;
	private static final String PRODUCTION_IP = "128.199.244.89";
	private static final String PRODUCTION_PORT = "8443";
	private static final String title = "DBLP";
	public static final String UPLOADS_PATH = "/Users/atton16/Documents/cs9321/workspace/asst2/WebContent/uploads/";
	private static Application app;
	private ServletContext servletContext;
	private DAOFactory daoFactory;
	private DAOSupport daoSupport;
	
	public static String getIpAddress() { 
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        String ipAddress=inetAddress.getHostAddress().toString();
                        System.out.print("IP address has problem");
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("get IP has problem!");
        }
        return null; 
}
	
	public static Application getSharedInstance() {
		if (app == null) {
			app = new Application();
		}
		return app;
	}

	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
		this.daoSupport = new DAOSupport();
		this.daoFactory = new DAOFactory();
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public String getContextPath() {
		return servletContext.getContextPath();
	}
	
	public String getTitle() {
		return title;
	}
	
	public Integer getListingCount() {
		Integer count = 0;
		ListingDAO dao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		try{
			count = dao.getListingCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<ListingDTO> getRandomPubs() {
		List<ListingDTO> results = new ArrayList<ListingDTO>();
		ListingDAO dao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		Integer count = getListingCount();
		Integer limit = 10;
		if(count < limit)
			limit = count;
		
		while(results.size() < limit) {
			List<ListingDTO> pubs = dao.getRandomPub();
			for(ListingDTO pub: pubs) {
				Boolean contain = false;
				for(ListingDTO item: results) {
					if(item.getPubID() == pub.getPubID()){
						contain = true;
						break;
					}
				}
				if(!contain)
					results.add(pub);
			}
		}
		return results;
	}
	
	public DAOFactory getDAOFactory() {
		return daoFactory;
	}
	
	public DAOSupport getDAOSupport() {
		return daoSupport;
	}
	
	public void destroy() {
		daoSupport.destroy();
	}
	
	public String getEnvironment() {
		return Application.ENV;
	}
	
	public String getProductionIP() {
		return Application.PRODUCTION_IP;
	}
	
	public String getProductionPort() {
		return Application.PRODUCTION_PORT;
	}
}

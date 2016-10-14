package com.sixppl.main;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.cmd.EmbedAdminCommand;
import com.sixppl.cmd.EmbedCartCommand;
import com.sixppl.cmd.EmbedUserCommand;
import com.sixppl.cmd.UserIsBannedCommand;
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
	private static final String title = "DBLP";
//	public static final String UPLOADS_PATH = "/Users/atton16/Documents/cs9321/workspace/asst2/WebContent/uploads/";
	public static final String UPLOADS_PATH = "/Users/liu100/Documents/workspace_neon/asst2/WebContent/uploads/";
//	public static final String UPLOADS_PATH = "/Users/Tanakrit/Desktop/COMP9321/asst2/WebContent/uploads/";
//	public static final String UPLOADS_PATH = "/Users/monai/Documents/workspace/asst2/WebContent/uploads/";
	private static final String TITLE_ATTRIBUTE = "title";
	private static final String CONTEXTPATH_ATTRIBUTE = "contextPath";
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
	
	public Integer getListingCountExceptPaused() {
		Integer count = 0;
		ListingDAO dao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		try{
			count = dao.getListingCountExceptPaused();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<ListingDTO> getRandomPubs(String sessionId) {
		List<ListingDTO> results = new ArrayList<ListingDTO>();
		ListingDAO dao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		Integer count = getListingCountExceptPaused();
		Integer limit = 10;
		if(count < limit)
			limit = count;
		
		while(results.size() < limit) {
			List<ListingDTO> pubs = dao.getRandomPub(sessionId);
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
	
	public void embedDefaults(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(TITLE_ATTRIBUTE, Application.getSharedInstance().getTitle());
		request.setAttribute(CONTEXTPATH_ATTRIBUTE, servletContext.getContextPath());
		try {
			new EmbedAdminCommand().execute(request,response);
			new EmbedUserCommand().execute(request,response);
			new EmbedCartCommand().execute(request,response);
			new UserIsBannedCommand().execute(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void incrementPageHitsCount(String Title) {
		daoFactory.getPageHitsDAO().incrementHitCount(Title);
	}
	
	public void addIP(String remoteAddress) {
		daoFactory.getIPLogDAO().add(remoteAddress);
	}
}

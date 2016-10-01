package com.sixppl.main;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session Event Listener
 * @author atton16
 *
 */
@WebListener()
public class Asst2SessionListener implements HttpSessionListener {
//	private static int ONE_MINUTE = 60;
//	private static int TWO_MINUTES = 120;
//	private static int FIVE_MINUTES = 300;
	private static int THIRTY_MINUTES = 1800;

	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		//TODO: Implement Session Activity Tracker
		//		for analytics purpose
//		Application.getSharedInstance().getSessions().add(evt.getSession().getId());
		evt.getSession().setMaxInactiveInterval(THIRTY_MINUTES);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		//TODO: Implement Session Activity Tracker
		//		for analytics purpose
//		Application.getSharedInstance().getSessions().remove(evt.getSession().getId());
	}

}

package com.rbac.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.rbac.common.SscConstant;

public class SessionListener implements HttpSessionAttributeListener,
		HttpSessionListener {
	private static int userNumber = 0;

	private static Set<HttpSession> sessions = new HashSet<HttpSession>();

	public void attributeAdded(HttpSessionBindingEvent arg0) {

		HttpSession session = arg0.getSession();
		if (session.getAttribute(SscConstant.USER) != null) {
			if (!sessions.contains(session)) {
				sessions.add(session);
				syncUserNumber();
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

	private void syncUserNumber() {
		userNumber = 0;
		Iterator<HttpSession> iter = sessions.iterator();
		while (iter.hasNext()) {
			HttpSession session = iter.next();
			if (session != null
					&& session.getAttribute(SscConstant.USER) != null) {
				userNumber += 1;
			}
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		if (session.getAttribute(SscConstant.USER) != null) {
			sessions.remove(session);
			syncUserNumber();
		}
	}

	public void sessionCreated(HttpSessionEvent arg0) {

	}

	public static Set<HttpSession> getSessions() {
		return sessions;
	}

	public static int getUserNumber() {
		return userNumber;
	}

}

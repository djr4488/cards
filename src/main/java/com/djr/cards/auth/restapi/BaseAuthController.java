package com.djr.cards.auth.restapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author dannyrucker
 *         Date: 3/12/14
 *         Time: 5:29 PM
 */
public class BaseAuthController {
	public HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		return session;
	}


	public String generateTrackingId(HttpServletRequest request) {
		String tracking = null;
		if (request.getSession(false) != null) {
			tracking = (String) request.getSession().getAttribute("tracking");
		}
		if (tracking == null) {
			tracking = UUID.randomUUID().toString();
		}
		return tracking;
	}
}

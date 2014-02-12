package com.djr.cards;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author dannyrucker
 *         Date: 1/30/14
 *         Time: 4:22 PM
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	@Inject
	private Logger logger;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getSessionAttribute(String attributeName) {
		return (String)getSession().getAttribute(attributeName);
	}

	public Object getAndRemoveSessionAttribute(String attributeName) {
		Object attribute = getSession().getAttribute(attributeName);
		getSession().removeAttribute(attributeName);
		return attribute;
	}

	public HttpSession getSession() {
		return request.getSession(false);
	}

    public HttpServletRequest getRequest() {
        return request;
    }

	public ActionContext getActionContext() {
		ActionContext actionContext = ActionContext.getContext();
		logger.debug("getActionContext() - actionContext:{}", actionContext.getName());
		return actionContext;
	}

	public String getIp() {
		return request.getRemoteAddr();
	}
}

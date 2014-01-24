package com.djr.cards.auth;

import com.djr.cards.audit.AuditService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/22/14
 * Time: 11:26 PM
 */
public class BaseAuthAction extends ActionSupport implements ModelDriven<AuthModel>, ServletRequestAware,
        ServletResponseAware {
    @Inject
    private org.slf4j.Logger logger;
    @Inject
    protected AuditService auditService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuthModel authModel = new AuthModel();

    public String getSessionAttribute(String attributeName) {
        return (String)getSession().getAttribute(attributeName);
    }

    public HttpSession getSession() {
        return request.getSession(false);
    }

    public ActionContext getActionContext() {
        ActionContext actionContext = ActionContext.getContext();
        logger.debug("getActionContext() - actionContext:{}", actionContext.getName());
        return actionContext;
    }

    public String getIp() {
        return request.getRemoteAddr();
    }

    @Override
    public AuthModel getModel() {
        return authModel;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}

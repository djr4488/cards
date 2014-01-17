package com.djr.cards;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/16/14
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseInterceptor extends AbstractInterceptor implements StrutsStatics {
    public ActionContext getContext(ActionInvocation invocation) {
        return invocation.getInvocationContext();
    }

    public HttpServletRequest getRequest(ActionInvocation invocation) {
        return (HttpServletRequest) getContext(invocation).get(HTTP_REQUEST);
    }

    public HttpSession getSession(ActionInvocation invocation, boolean canCreate) {
        return getRequest(invocation).getSession(canCreate);
    }

    public abstract String intercept(ActionInvocation actionInvocation) throws Exception;
}

package com.djr.cards.login;

import com.djr.cards.audit.AuditService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/10/14
 * Time: 7:37 PM
 */
public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {
    @Inject
    private org.slf4j.Logger logger;
    @Inject
    private AuditService auditService;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        logger.info("intercept() incoming request");
        final ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.getContext().get(HTTP_REQUEST);
        HttpSession session = request.getSession();
        if (session.getAttribute("tracking") == null) {
            UUID uuid = UUID.randomUUID();
            session.setAttribute("tracking", uuid.toString());
        }
        logger.debug("intercept() tracking:{}", session.getAttribute("tracking"));
        auditService.writeAudit(auditService.getAuditLog(session.getAttribute("tracking").toString(),
                "LoginInterceptor.intercept()", "NA", Calendar.getInstance()));
        return actionInvocation.invoke();
    }
}

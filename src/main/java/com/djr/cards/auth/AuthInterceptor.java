package com.djr.cards.auth;

import com.djr.cards.BaseInterceptor;
import com.djr.cards.audit.AuditService;
import com.opensymphony.xwork2.ActionInvocation;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.UUID;

/**
 * User: djr4488
 * Date: 1/10/14
 * Time: 7:37 PM
 */
public class AuthInterceptor extends BaseInterceptor {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditService;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        logger.info("intercept() incoming request");
        HttpSession session = getSession(actionInvocation, true);
        if (session.getAttribute("tracking") == null) {
            UUID uuid = UUID.randomUUID();
            session.setAttribute("tracking", uuid.toString());
        }
        logger.debug("intercept() tracking:{}", session.getAttribute("tracking"));
        auditService.writeAudit(auditService.getAuditLog(session.getAttribute("tracking").toString(),
                "AuthInterceptor.intercept()", "NA", Calendar.getInstance()));
        return actionInvocation.invoke();
    }
}

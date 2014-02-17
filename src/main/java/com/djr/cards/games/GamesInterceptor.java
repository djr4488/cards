package com.djr.cards.games;

import com.djr.cards.BaseInterceptor;
import com.djr.cards.data.entities.User;
import com.opensymphony.xwork2.ActionInvocation;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * User: djr4488
 * Date: 1/16/14
 * Time: 9:07 PM
 */
public class GamesInterceptor extends BaseInterceptor {
    @Inject
    private Logger logger;
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        logger.info("intercept() actionInvocation:{}", actionInvocation);
        HttpSession session = getSession(actionInvocation, false);
        if (session != null) {
            User user = (User)session.getAttribute("user");
            if (user == null) {
                return "landing";
            }
            if (session.getAttribute("tracking") == null) {
                return "landing";
            }
        }
        return actionInvocation.invoke();
    }
}

package com.djr.cards.games;

import com.djr.cards.auth.service.AuthTokenService;
import com.djr.cards.data.entities.User;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 11:00 PM
 */
public class BaseGameController {
    @Inject
    private Logger log;
    @Inject
    private AuthTokenService authTokenSvc;

    public HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        return session;
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        return authTokenSvc.isValidToken(token, (String)getSession(request).getAttribute("tracking"),
                ((User)getSession(request).getAttribute("user")).emailAddress);
    }
}

package com.djr.cards.auth.filter;

import com.djr.cards.auth.service.AuthTokenService;
import com.djr.cards.data.entities.User;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: djr4488
 * Date: 3/22/2014
 * Time: 9:51 AM
 *
 * Extremely simple auth verification, easily defeated without SSL not as though I am protecting much here though
 */
@WebFilter(filterName="authFilter",
    urlPatterns={"/cardsapi/golfStats/get", "/cardsapi/gameSelection/submit", "/cardsapi/gamesvc/create/submit"})
public class AuthFilter implements Filter {
    @Inject
    private Logger log;
    @Inject
    private AuthTokenService authTokenSvc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public boolean validateToken(String token, HttpSession session) {
        return authTokenSvc.isValidToken(token, (String)session.getAttribute("tracking"),
                ((User)session.getAttribute("user")).emailAddress);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession(false);
        String token = req.getHeader("auth");
        log.info("doFilter() - token:{}", token);
        if (token != null && validateToken(token, session)) {
            log.debug("doFilter() - validated");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.debug("doFilter() - not validated");
            HttpServletResponse resp = (HttpServletResponse)servletResponse;
            String url = req.getRequestURL().toString();
            log.debug("doFilter() - url:{}", url);
//            url = url.substring(0, url.indexOf("cardsapi"));
//            log.debug("doFilter() - url after substring:{}", url);
            //resp.sendRedirect(url + "cards/index.html");
            resp.setStatus(500);
            log.debug("doFilter() - resp:{}", resp);
        }
    }

    @Override
    public void destroy() {

    }
}

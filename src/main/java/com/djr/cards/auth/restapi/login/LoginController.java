package com.djr.cards.auth.restapi.login;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.AuthService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.djr.cards.auth.service.LoginResult;
import com.djr.cards.auth.util.HashingUtil;
import org.slf4j.Logger;

import java.util.UUID;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 7:57 AM
 */
public abstract class LoginController {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditSvc;
    @Inject
    private AuthService authSvc;
    @Inject
    private HashingUtil hashingUtil;

    private HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        return session;
    }


    private String generateTrackingId(HttpServletRequest request) {
        String tracking = null;
        if (request.getSession(false) != null) {
            tracking = (String)request.getSession().getAttribute("tracking");
        }
        if (tracking == null) {
            tracking = UUID.randomUUID().toString();
        }
        return tracking;
    }

    @POST
    @Path("/submit")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse submit(LoginForm restLoginForm, @Context HttpServletRequest request) {
        String tracking = generateTrackingId(request);
        HttpSession session = getSession(request);
        logger.debug("submit() - restLoginForm:{}, tracking:{}", restLoginForm, tracking);
        session.setAttribute("tracking", tracking);
        //TODO: validation logic goes here
        AuthModel authModel = restLoginForm.getAuthModel();
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        LoginResult result = authSvc.login(authModel, request.getRemoteAddr());
        LoginResponse loginResponse;
        if (result.result == LoginResult.ResultOptions.SUCCESS) {
            session.removeAttribute("user");
            session.setAttribute("user", result.user);
            loginResponse = new LoginResponse();
            loginResponse.nextLanding = "selectGame";
            loginResponse.token = hashingUtil.generateHmacHash(("<userName>"+authModel.getUserName()+
                    "</userName><tracking>"+tracking+"</tracking>"));
            return loginResponse;
        } else {
            loginResponse = new LoginResponse();
			loginResponse.errorBold = "Slow down, Sparky!";
            loginResponse.errorMsg = "Check your email/password combination.";
            return loginResponse;
        }
    }
}

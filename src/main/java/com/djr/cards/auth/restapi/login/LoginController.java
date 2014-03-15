package com.djr.cards.auth.restapi.login;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthResponse;
import com.djr.cards.auth.restapi.BaseAuthController;
import com.djr.cards.auth.service.AuthService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.djr.cards.auth.service.LoginResult;
import com.djr.cards.auth.util.HashingUtil;
import org.slf4j.Logger;
import java.util.Calendar;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 7:57 AM
 */
public abstract class LoginController extends BaseAuthController {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditSvc;
    @Inject
    private AuthService authSvc;
    @Inject
    private HashingUtil hashingUtil;

    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthResponse submit(LoginForm restLoginForm, @Context HttpServletRequest request) {
        String tracking = generateTrackingId(request);
        HttpSession session = getSession(request);
        logger.debug("submit() - restLoginForm:{}, tracking:{}", restLoginForm, tracking);
		auditSvc.writeAudit(auditSvc.getAuditLog(tracking, "CreateAccountController.submit()",
				request.getRemoteAddr(), Calendar.getInstance()));
        session.removeAttribute("tracking");
        session.setAttribute("tracking", tracking);
        //TODO: validation logic goes here
        AuthModel authModel = restLoginForm.getAuthModel();
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        LoginResult result = authSvc.login(authModel, request.getRemoteAddr());
        return getAuthResponse(tracking, authModel, result, session);
    }

    private AuthResponse getAuthResponse(String tracking, AuthModel authModel, LoginResult result,
                                         HttpSession session) {
        AuthResponse loginResponse;
        if (result.result == LoginResult.ResultOptions.SUCCESS) {
            session.removeAttribute("user");
            session.setAttribute("user", result.user);
            loginResponse = new AuthResponse();
            loginResponse.nextLanding = "selectGame";
            loginResponse.token = hashingUtil.generateHmacHash("<userName>"+authModel.getUserName()+
                    "</userName><tracking>"+tracking+"</tracking>");
            logger.debug("submit() - loginResponse:{}", loginResponse);
            return loginResponse;
        } else {
            loginResponse = new AuthResponse();
			loginResponse.errorBold = "Slow down, Sparky!";
            loginResponse.errorMsg = "Check your email/password combination.";
            return loginResponse;
        }
    }
}

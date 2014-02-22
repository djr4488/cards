package com.djr.cards.auth.restapi.login;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.AuthService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.djr.cards.auth.service.LoginResult;
import org.slf4j.Logger;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 7:57 AM
 */
public abstract class RestLoginFormController {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditSvc;
    @Inject
    private AuthService authSvc;

    @POST
    @Path("/login/submit")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse submit(RestLoginForm restLoginForm, @Context HttpServletRequest request) {
        logger.debug("submit() - restLoginForm:{}", restLoginForm);
        //TODO: validation logic goes here
        AuthModel authModel = restLoginForm.getAuthModel();
        LoginResult result = authSvc.login(authModel, request.getRemoteAddr());
        LoginResponse loginResponse;
        if (result.result == LoginResult.ResultOptions.SUCCESS) {
            loginResponse = new LoginResponse();
            loginResponse.nextLanding = "selectGame";
            return loginResponse;
        } else {
            loginResponse = new LoginResponse();
            loginResponse.errorMsg = "Slow down, Sparky!  Check user/pass combination.";
            return loginResponse;
        }
    }
}

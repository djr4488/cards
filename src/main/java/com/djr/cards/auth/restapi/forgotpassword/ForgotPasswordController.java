package com.djr.cards.auth.restapi.forgotpassword;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthResponse;
import com.djr.cards.auth.restapi.BaseAuthController;
import com.djr.cards.auth.service.AuthService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 3/13/14
 * Time: 9:46 PM
 */
public class ForgotPasswordController extends BaseAuthController {
    @Inject
    private Logger logger;
    @Inject
    private AuthService authSvc;
    @Inject
    private AuditService auditSvc;

    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthResponse submit(ForgotPasswordForm forgotPasswordForm, @Context HttpServletRequest request) {
        String tracking = generateTrackingId(request);
        getSession(request).setAttribute("tracking", tracking);
        logger.info("submit() - forgotPasswordForm:{}", forgotPasswordForm);
        auditSvc.writeAudit(auditSvc.getAuditLog(tracking,
                "ForgotPasswordController.submit()", request.getRemoteAddr(), Calendar.getInstance()));
        AuthModel am = forgotPasswordForm.getAuthModel();
        AuthService.ForgotPasswordResult result = authSvc.forgotPassword(am, tracking);
        AuthResponse authResponse = null;
        authResponse = new AuthResponse();
        if (AuthService.ForgotPasswordResult.SUCCESS == result) {
            authResponse.nextLanding ="resetPassword";
        } else {
            authResponse.errorMsg = "I didn't right recognize you.  Is your email address correct?";
            authResponse.errorBold = "Howdy Partner!";
        }
        return authResponse;
    }
}

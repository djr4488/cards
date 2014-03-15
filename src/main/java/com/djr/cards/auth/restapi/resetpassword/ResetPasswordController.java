package com.djr.cards.auth.restapi.resetpassword;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthResponse;
import com.djr.cards.auth.restapi.BaseAuthController;
import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.util.HashingUtil;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;

/**
 * @author dannyrucker
 *         Date: 3/14/14
 *         Time: 2:08 PM
 */
public abstract class ResetPasswordController extends BaseAuthController {
	@Inject
	private Logger log;
	@Inject
	private AuditService auditSvc;
	@Inject
	private AuthService authSvc;
	@Inject
	private HashingUtil hashingUtil;

	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	public AuthResponse submit(ResetPasswordForm resetPasswordForm, @Context HttpServletRequest request) {
		AuthResponse authResponse = new AuthResponse();
		String tracking = generateTrackingId(request);
		getSession(request).setAttribute("tracking", tracking);
		log.debug("submit() - resetPasswordForm:{}", resetPasswordForm);
		auditSvc.writeAudit(auditSvc.getAuditLog(tracking, "ResetPasswordController.sumbit()",
				request.getRemoteAddr(), Calendar.getInstance()));
		AuthModel authModel = resetPasswordForm.getAuthModel();
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        authModel.setConfirmPassword(hashingUtil.generateHmacHash(authModel.getConfirmPassword()));
		//TODO - validate the information here for password/confirmPassword matching, etc.
		AuthService.ChangePasswordResult result = authSvc.changePassword(authModel, tracking);
		if (result == AuthService.ChangePasswordResult.SUCCESS) {
			authResponse.nextLanding = "login";
			authResponse.msgBold = "Bazinga!  ";
			authResponse.msg  = "You reset your password!  You may now login.";
		} else {
			authResponse.errorMsg = "I'd check that the email address and/or security code is correct.  And that the " +
					"password and confirm passwords did infact match.";
			authResponse.errorBold = "Oops!  ";
		}
		return authResponse;
	}
}

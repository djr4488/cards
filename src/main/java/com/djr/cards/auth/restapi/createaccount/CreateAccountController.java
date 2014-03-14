package com.djr.cards.auth.restapi.createaccount;

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
 *         Date: 3/12/14
 *         Time: 5:04 PM
 */
public abstract class CreateAccountController extends BaseAuthController {
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
	public AuthResponse submit(CreateAccountForm createAccountForm, @Context HttpServletRequest request) {
		AuthResponse authResponse = null;
		String tracking = generateTrackingId(request);
		log.info("submit() createAccountForm:{}, tracking:{}", createAccountForm, tracking);
		auditSvc.writeAudit(auditSvc.getAuditLog(tracking, "CreateAccountController.submit()",
				request.getRemoteAddr(), Calendar.getInstance()));
		getSession(request).setAttribute("tracking", tracking);
		AuthModel authModel = createAccountForm.getAuthModel();
		if (authModel.getPassword().equals(authModel.getConfirmPassword())) {
			authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
			authModel.setConfirmPassword(hashingUtil.generateHmacHash(authModel.getConfirmPassword()));
			AuthService.CreateResult result = authSvc.createUser(authModel, tracking);
			if (result == AuthService.CreateResult.CREATED) {
				authResponse = new AuthResponse();
				authResponse.nextLanding = "login";
				authResponse.msg = "You now have an account in Cards!";
				authResponse.msgBold = "Hot damn!  ";
			} else {
				authResponse = new AuthResponse();
				authResponse.errorMsg = "Something went wrong creating your account.  Likely the email address already exists.";
				authResponse.errorBold = "Danger Will Robinson!";
			}
		} else {
			authResponse = new AuthResponse();
			authResponse.errorMsg = "Check your information, but appears that your passwords don't match";
			authResponse.errorBold = "Slow down, Sparky!";
		}
		return authResponse;
	}
}

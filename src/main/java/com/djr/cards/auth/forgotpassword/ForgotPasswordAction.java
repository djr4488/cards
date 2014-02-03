package com.djr.cards.auth.forgotpassword;

import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.BaseAuthAction;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 1/22/14
 * Time: 9:31 PM
 */
public class ForgotPasswordAction extends BaseAuthAction {
    @Inject
    @Default
    private Logger logger;

    public void validate() {
		logger.debug("validate() - authModel:{}", getModel());
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("forgot.password.missing.username"));
        }
    }

	@SkipValidation
    public String forgotPassword() {
        logger.info("forgotPassword()");
        return "success";
    }

    public String forgotPasswordExecute() {
        logger.info("forgotPasswordExecute() - authModel:{}", getModel());
        auditService.writeAudit(auditService.getAuditLog(getSessionAttribute("tracking"),
                "ForgotPasswordAction.forgotPasswordExecute()", getIp(), Calendar.getInstance()));
        AuthService.ForgotPasswordResult result = authService.forgotPassword(getModel(),
                getSessionAttribute("tracking"));
        if (result == AuthService.ForgotPasswordResult.NOT_FOUND ||
                result == AuthService.ForgotPasswordResult.SUCCESS) {
            return "success";
        }
        addActionError(getText("forgot.password.execute.error"));
        return "error";
    }
}

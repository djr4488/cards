package com.djr.cards.auth.forgotpassword;

import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.BaseAuthAction;
import com.djr.cards.auth.HashingUtil;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 1/22/14
 * Time: 9:31 PM
 */
public class ForgotPasswordAction extends BaseAuthAction {
    @Inject
    @Default
    private Logger logger;
    @Inject
    private AuthService authService;
    @Inject
    private HashingUtil hashingUtil;

    public void validate() {
        logger.debug("validate() - authModel:{}", getModel());
        if (getActionContext().getName().equalsIgnoreCase("forgotPasswordLanding")) {
            return;
        }
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("forgot.password.missing.username"));
        }
    }

    public String forgotPassword() {
        logger.info("forgotPassword()");
        return "success";
    }

    public String forgotPasswordExecute() {
        logger.info("forgotPasswordExecute() - authModel:{}", getModel());
        AuthService.ForgotPasswordResult result = authService.forgotPassword(getModel(),
                getSessionAttribute("tracking"));
        if (result == AuthService.ForgotPasswordResult.NOT_FOUND ||
                result == AuthService.ForgotPasswordResult.SUCCESS) {
            return "success";
        }
        addActionError("Something failed while sending you an email to change password.  Can you try again?");
        return "error";
    }
}

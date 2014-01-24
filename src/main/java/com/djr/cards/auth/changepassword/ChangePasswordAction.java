package com.djr.cards.auth.changepassword;

import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.BaseAuthAction;
import com.djr.cards.auth.HashingUtil;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/22/14
 * Time: 9:45 PM
 */
public class ChangePasswordAction extends BaseAuthAction {
    @Inject
    @Default
    private Logger logger;
    @Inject
    private AuthService authService;
    @Inject
    private HashingUtil hashingUtil;

    public void validate() {
        logger.debug("validate() - authModel:{}", getModel());
        if (getActionContext().getName().equalsIgnoreCase("changePasswordLanding")) {
            return;
        }
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("reset.missing.username"));
        }
        if (getModel().getRandomString() == null || getModel().getRandomString().trim().length() == 0) {
            logger.debug("validate() - confirmation code missing");
            addFieldError("alias", getText("reset.missing.confirmation.code"));
        }
        if(getModel().getPassword() == null || getModel().getConfirmPassword() == null ||
                getModel().getPassword().trim().length() == 0 ||
                getModel().getConfirmPassword().trim().length() == 0) {
            logger.debug("validate() - password or confirm password was missing");
            addFieldError("confirmPassword", getText("reset.missing.passwords"));
        } else if (!getModel().getPassword().equals(getModel().getConfirmPassword())) {
            logger.debug("validate() - passwords not equal");
            addFieldError("confirmPassword", getText("reset.no.match.passwords"));
        }
    }

    public String changePassword() {
        logger.info("changePassword()");
        return "success";
    }

    public String changePasswordExecute() {
        logger.info("changePasswordExecute() - authModel:{}", getModel());
        auditService.writeAudit(auditService.getAuditLog(getSessionAttribute("tracking"),
                "ChangePasswordAction.changePasswordExecute()", getIp(), Calendar.getInstance()));
        getModel().setPassword(hashingUtil.generateHmacHash(getModel().getPassword()));
        getModel().setConfirmPassword(hashingUtil.generateHmacHash(getModel().getConfirmPassword()));
        AuthService.ChangePasswordResult result = authService.changePassword(getModel(),
                getSessionAttribute("tracking"));
        if (result == AuthService.ChangePasswordResult.SUCCESS  ||
                result == AuthService.ChangePasswordResult.NOT_FOUND) {
            return "success";
        }
        addActionError(getText("reset.password.execute.error"));
        return "error";
    }
}

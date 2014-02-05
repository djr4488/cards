package com.djr.cards.auth.create;

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
 * Time: 9:17 PM
 */
public class CreateAccountAction extends BaseAuthAction {
    @Inject
    private Logger logger;

    public void validate() {
        logger.debug("validate() - authModel:{}", getModel());
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("create.missing.username"));
        }
        if (getModel().getAlias() == null || getModel().getAlias().trim().length() == 0) {
            logger.debug("validate() - user name was missing");
            addFieldError("alias", getText("create.missing.alias"));
        }
        if(getModel().getPassword() == null || getModel().getConfirmPassword() == null ||
                getModel().getPassword().trim().length() == 0 ||
                getModel().getConfirmPassword().trim().length() == 0) {
            logger.debug("validate() - password or confirm password was missing");
            addFieldError("confirmPassword", getText("create.missing.passwords"));
        } else if (!getModel().getPassword().equals(getModel().getConfirmPassword())) {
            logger.debug("validate() - passwords not equal");
            addFieldError("confirmPassword", getText("create.no.match.passwords"));
        }
    }

	@SkipValidation
    public String createAccountLanding() {
        logger.info("createAccountLanding - landed");
        return "createAcct";
    }

    public String createAccountExecute() {
        logger.info("createAccountExecute - authModel:{}", getModel());
        auditService.writeAudit(auditService.getAuditLog(getSessionAttribute("tracking"),
                "CreateAccountAction.createAccountExecute()", getIp(), Calendar.getInstance()));
        getModel().setPassword(hashingUtil.generateHmacHash(getModel().getPassword()));
        getModel().setConfirmPassword(hashingUtil.generateHmacHash(getModel().getConfirmPassword()));
        AuthService.CreateResult createResult = authService.createUser(getModel(), getSessionAttribute("tracking"));
        if (createResult == AuthService.CreateResult.CREATED) {
            return "success";
		}
		addActionError(getText("create.account.execute.error"));
		return "error";
    }
}

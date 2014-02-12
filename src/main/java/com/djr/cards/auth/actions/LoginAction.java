package com.djr.cards.auth.actions;

import com.djr.cards.auth.BaseAuthAction;
import com.djr.cards.auth.login.LoginResult;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:22 PM
 */
public class LoginAction extends BaseAuthAction {
    @Inject
    private Logger logger;

    public void validate() {
        logger.debug("validate() - authModel:{}", getModel());
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("login.missing.username"));
        }
        if(getModel().getPassword() == null ||getModel().getPassword().trim().length() == 0) {
            logger.debug("validate() - password was missing");
            addFieldError("password", getText("login.missing.password"));
        }
    }

	@SkipValidation
    public String loginLoad() {
        logger.info("loginLoad() - landed with tracking:{}", getSessionAttribute("tracking"));
        return "success";
    }

    public String loginExecute() {
        getModel().setPassword(hashingUtil.generateHmacHash(getModel().getPassword()));
        logger.info("loginExecute - authModel:{}, tracking:{}", getModel(), getSessionAttribute("tracking"));
        auditService.writeAudit(auditService.getAuditLog(getSessionAttribute("tracking"),
                "LoginAction.loginExecute()", getIp(), Calendar.getInstance()));
        LoginResult loginResult = authService.login(getModel(), getSessionAttribute("tracking"));
        if (loginResult.result == LoginResult.ResultOptions.SUCCESS) {
            getSession().setAttribute("user", loginResult.user);
            return "success";
        }
		getRequest().setAttribute("msgbold", "error.login.failed.bold");
		getRequest().setAttribute("msgtext", "error.login.failed.text");
        return "error";
    }
}

package com.djr.cards.auth.login;

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
 * Date: 1/4/14
 * Time: 5:22 PM
 */
public class LoginAction extends BaseAuthAction {
    @Inject @Default
    private Logger logger;
    @Inject
    private AuthService authService;
    @Inject
    private HashingUtil hashingUtil;

    public void validate() {
        logger.debug("validate() - authModel:{}", getModel());
        if (getActionContext().getName().equalsIgnoreCase("loginLoad")) {
            return;
        }
        if (getModel().getUserName() == null || getModel().getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("login.missing.username"));
        }
        if(getModel().getPassword() == null ||getModel().getPassword().trim().length() == 0) {
            logger.debug("validate() - password was missing");
            addFieldError("password", getText("login.missing.password"));
        }
    }

    public String loginLoad() {
        logger.info("loginLoad() - landed");
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
        addActionError(getText("login.no.user"));
        return "error";
    }
}

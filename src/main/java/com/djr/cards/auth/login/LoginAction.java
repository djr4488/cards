package com.djr.cards.auth.login;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.BaseAuthAction;
import com.djr.cards.auth.HashingUtil;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

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
    private AuthModel authModel = new AuthModel();

    public void validate() {
        logger.debug("validate() - authModel:{}", authModel);
        if (getActionContext().getName().equalsIgnoreCase("loginLoad")) {
            return;
        }
        if (authModel.getUserName() == null || authModel.getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("login.missing.username"));
        }
        if(authModel.getPassword() == null ||authModel.getPassword().trim().length() == 0) {
            logger.debug("validate() - password was missing");
            addFieldError("password", getText("login.missing.password"));
        }
    }

    public String loginLoad() {
        logger.info("loginLoad() - landed");
        return "login";
    }

    public String loginExecute() {
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        logger.info("loginExecute - authModel:{}", authModel);
        LoginResult loginResult = authService.login(authModel, getSessionAttribute("tracking"));
        if (loginResult.result == LoginResult.ResultOptions.SUCCESS) {
            getSession().setAttribute("user", loginResult.user);
            return "success";
        }
        addActionError(getText("login.no.user"));
        return "error";
    }
}

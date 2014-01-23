package com.djr.cards.auth.changepassword;

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
    private AuthModel authModel = new AuthModel();

    public void validate() {
        logger.debug("validate() - authModel:{}", authModel);
        if (getActionContext().getName().equalsIgnoreCase("changePasswordLanding")) {
            return;
        }
        if (authModel.getUserName() == null || authModel.getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("reset.missing.username"));
        }
        if (authModel.getRandomString() == null || authModel.getRandomString().trim().length() == 0) {
            logger.debug("validate() - confirmation code missing");
            addFieldError("alias", getText("reset.missing.confirmation.code"));
        }
        if(authModel.getPassword() == null || authModel.getConfirmPassword() == null ||
                authModel.getPassword().trim().length() == 0 ||
                authModel.getConfirmPassword().trim().length() == 0) {
            logger.debug("validate() - password or confirm password was missing");
            addFieldError("confirmPassword", getText("reset.missing.passwords"));
        } else if (!authModel.getPassword().equals(authModel.getConfirmPassword())) {
            logger.debug("validate() - passwords not equal");
            addFieldError("confirmPassword", getText("reset.no.match.passwords"));
        }
    }

    public String changePassword() {
        logger.info("changePassword()");
        return "success";
    }

    public String changePasswordExecute() {
        logger.info("changePasswordExecute() - authModel:{}", authModel);
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        authModel.setConfirmPassword(hashingUtil.generateHmacHash(authModel.getConfirmPassword()));
        AuthService.ChangePasswordResult result = authService.changePassword(authModel,
                getSessionAttribute("tracking"));
        if (result == AuthService.ChangePasswordResult.SUCCESS  ||
                result == AuthService.ChangePasswordResult.NOT_FOUND) {
            return "success";
        }
        addActionError("For some reason couldn't change your password!  Maybe you can try again later?");
        return "error";
    }
}

package com.djr.cards.login;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:22 PM
 */
public class LoginAction extends ActionSupport implements ModelDriven<LoginModel>, ServletRequestAware,
        ServletResponseAware {
    @Inject @Default
    private Logger logger;
    @Inject
    private LoginService loginService;
    @Inject
    private HashingUtil hashingUtil;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private LoginModel loginModel = new LoginModel();

    private String getSessionAttribute(String attributeName) {
        return (String)getSession().getAttribute(attributeName);
    }

    private HttpSession getSession() {
        return request.getSession(false);
    }

    @Override
    public LoginModel getModel() {
        return loginModel;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public String loginLoad() {
        logger.info("loginLoad() - landed");
        return "login";
    }

    public String loginExecute() {
        loginModel.setPassword(hashingUtil.generateHmacHash(loginModel.getPassword()));
        logger.info("loginExecute - loginModel:{}", loginModel);
        LoginResult loginResult = loginService.login(loginModel, getSessionAttribute("tracking"));
        if (loginResult.result == LoginResult.ResultOptions.SUCCESS) {
            getSession().setAttribute("user", loginResult.user);
            return "success";
        }
        addActionError("Email address or password is incorrect.");
        return "error";
    }

    public String loginCreateAccount() {
        logger.info("loginCreateAccount - landed");
        return "createAcct";
    }

    private String validateLoginModel() {
        logger.debug("validateLoginModel() - loginModel:{}", loginModel);
        String validateMessage = null;
        if (loginModel.getEmail() == null || loginModel.getEmail().trim().length() == 0) {
            logger.debug("validateLoginModel() - email was missing");
            validateMessage = "Email address is required.";
        } else if (loginModel.getUserName() == null || loginModel.getUserName().trim().length() == 0) {
            logger.debug("validateLoginModel() - user name was missing");
            validateMessage = "User name is required.";
        } else if(loginModel.getPassword() == null || loginModel.getConfirmPassword() == null ||
                loginModel.getPassword().trim().length() == 0 ||
                loginModel.getConfirmPassword().trim().length() == 0) {
            logger.debug("validateLoginModel() - password or confirm password was missing");
            validateMessage = "Passwords are required.";
        } else if (!loginModel.getPassword().equals(loginModel.getConfirmPassword())) {
            logger.debug("validateLoginModel() - passwords not equal");
            validateMessage = "Passwords are not equal.";
        }
        return validateMessage;
    }

    public String createAccountExecute() {
        logger.info("createAccountExecute - loginModel:{}", loginModel);
        loginModel.setPassword(hashingUtil.generateHmacHash(loginModel.getPassword()));
        loginModel.setConfirmPassword(hashingUtil.generateHmacHash(loginModel.getConfirmPassword()));
        String validateMessage = validateLoginModel();
        if (validateMessage != null && validateMessage.trim().length() > 0) {
            addActionError(validateMessage);
            return "error";
        }
        LoginService.CreateResult createResult = loginService.createUser(loginModel, getSessionAttribute("tracking"));
        if (createResult == LoginService.CreateResult.CREATED) {
            return "success";
        } else {
            addActionError("Login email already exists.");
            return "error";
        }
    }

    public String forgotPassword() {
        logger.info("forgotPassword()");
        return "success";
    }

    public String forgotPasswordExecute() {
        logger.info("forgotPasswordExecute() - loginModel:{}", loginModel);
        LoginService.ForgotPasswordResult result = loginService.forgotPassword(loginModel,
                getSessionAttribute("tracking"));
        if (result == LoginService.ForgotPasswordResult.NOT_FOUND ||
                result == LoginService.ForgotPasswordResult.SUCCESS) {
            return "success";
        }
        addActionError("Something failed while sending you an email to change password.  Can you try again?");
        return "error";
    }

    public String changePassword() {
        logger.info("changePassword()");
        return "success";
    }

    public String changePasswordExecute() {
        logger.info("changePasswordExecute() - loginModel:{}", loginModel);
        loginModel.setPassword(hashingUtil.generateHmacHash(loginModel.getPassword()));
        loginModel.setConfirmPassword(hashingUtil.generateHmacHash(loginModel.getConfirmPassword()));
        LoginService.ChangePasswordResult result = loginService.changePassword(loginModel,
                getSessionAttribute("tracking"));
        if (result == LoginService.ChangePasswordResult.SUCCESS  ||
                result == LoginService.ChangePasswordResult.NOT_FOUND) {
            return "success";
        }
        addActionError("For some reason couldn't change your password!  Maybe you can try again later?");
        return "error";
    }
}

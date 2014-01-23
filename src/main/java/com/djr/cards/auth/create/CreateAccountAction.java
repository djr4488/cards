package com.djr.cards.auth.create;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.HashingUtil;
import com.opensymphony.xwork2.ActionContext;
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
 * Date: 1/22/14
 * Time: 9:17 PM
 */
public class CreateAccountAction extends ActionSupport implements ModelDriven<AuthModel>, ServletRequestAware,
        ServletResponseAware {
    @Inject
    @Default
    private Logger logger;
    @Inject
    private AuthService authService;
    @Inject
    private HashingUtil hashingUtil;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuthModel authModel = new AuthModel();

    private String getSessionAttribute(String attributeName) {
        return (String)getSession().getAttribute(attributeName);
    }

    private HttpSession getSession() {
        return request.getSession(false);
    }

    @Override
    public AuthModel getModel() {
        return authModel;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public void validate() {
        logger.debug("validate() - authModel:{}", authModel);
        ActionContext actionContext = ActionContext.getContext();
        logger.debug("validate() - actionContext:{}", actionContext.getName());
        if (actionContext.getName().equalsIgnoreCase("createAccountLanding")) {
            return;
        }
        if (authModel.getUserName() == null || authModel.getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("create.missing.username"));
        }
        if (authModel.getAlias() == null || authModel.getAlias().trim().length() == 0) {
            logger.debug("validate() - user name was missing");
            addFieldError("alias", getText("create.missing.alias"));
        }
        if(authModel.getPassword() == null || authModel.getConfirmPassword() == null ||
                authModel.getPassword().trim().length() == 0 ||
                authModel.getConfirmPassword().trim().length() == 0) {
            logger.debug("validate() - password or confirm password was missing");
            addFieldError("confirmPassword", getText("create.missing.passwords"));
        } else if (!authModel.getPassword().equals(authModel.getConfirmPassword())) {
            logger.debug("validate() - passwords not equal");
            addFieldError("confirmPassword", getText("create.no.match.passwords"));
        }
    }

    public String createAccountLanding() {
        logger.info("createAccountLanding - landed");
        return "createAcct";
    }

    public String createAccountExecute() {
        logger.info("createAccountExecute - authModel:{}", authModel);
        authModel.setPassword(hashingUtil.generateHmacHash(authModel.getPassword()));
        authModel.setConfirmPassword(hashingUtil.generateHmacHash(authModel.getConfirmPassword()));
        AuthService.CreateResult createResult = authService.createUser(authModel, getSessionAttribute("tracking"));
        if (createResult == AuthService.CreateResult.CREATED) {
            return "success";
        } else {
            addActionError("Login email already exists.");
            return "error";
        }
    }
}

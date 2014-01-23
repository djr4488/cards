package com.djr.cards.auth.forgotpassword;

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
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForgotPasswordAction extends ActionSupport implements ModelDriven<AuthModel>, ServletRequestAware,
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
        if (actionContext.getName().equalsIgnoreCase("forgotPasswordLanding")) {
            return;
        }
        if (authModel.getUserName() == null || authModel.getUserName().trim().length() == 0) {
            logger.debug("validate() - email was missing");
            addFieldError("userName", getText("forgot.password.missing.username"));
        }
    }

    public String forgotPassword() {
        logger.info("forgotPassword()");
        return "success";
    }

    public String forgotPasswordExecute() {
        logger.info("forgotPasswordExecute() - authModel:{}", authModel);
        AuthService.ForgotPasswordResult result = authService.forgotPassword(authModel,
                getSessionAttribute("tracking"));
        if (result == AuthService.ForgotPasswordResult.NOT_FOUND ||
                result == AuthService.ForgotPasswordResult.SUCCESS) {
            return "success";
        }
        addActionError("Something failed while sending you an email to change password.  Can you try again?");
        return "error";
    }
}

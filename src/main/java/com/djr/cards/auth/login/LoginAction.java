package com.djr.cards.auth.login;

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
 * Date: 1/4/14
 * Time: 5:22 PM
 */
public class LoginAction extends ActionSupport implements ModelDriven<AuthModel>, ServletRequestAware,
        ServletResponseAware {
    @Inject @Default
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
        if (actionContext.getName().equalsIgnoreCase("loginLoad")) {
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

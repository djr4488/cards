package com.djr.cards.auth;

import com.djr.cards.BaseAction;
import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.util.HashingUtil;
import com.opensymphony.xwork2.ModelDriven;

import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 1/22/14
 * Time: 11:26 PM
 */
public class BaseAuthAction extends BaseAction implements ModelDriven<AuthModel> {
    @Inject
    private org.slf4j.Logger logger;
    @Inject
    protected AuditService auditService;
	@Inject
	protected AuthService authService;
	@Inject
	protected HashingUtil hashingUtil;
    private AuthModel authModel = new AuthModel();

    @Override
    public AuthModel getModel() {
        return authModel;
	}
}

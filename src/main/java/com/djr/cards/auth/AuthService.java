package com.djr.cards.auth;

import com.djr.cards.auth.login.LoginResult;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:07 PM
 */
public interface AuthService {
    public enum CreateResult { CREATED, USERNAME_TAKEN, OTHER_FAILURE, NOT_IMPLEMENTED };
    public enum ForgotPasswordResult { SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED };
    public enum ChangePasswordResult { SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED };
    public CreateResult createUser(AuthModel authModel, String trackingId);
    public LoginResult login(AuthModel authModel, String trackingId);
    public ForgotPasswordResult forgotPassword(AuthModel authModel, String trackingId);
    public ChangePasswordResult changePassword(AuthModel authModel, String trackingId);
}

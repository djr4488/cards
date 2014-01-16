package com.djr.cards.login;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:07 PM
 */
public interface LoginService {
    public enum CreateResult { CREATED, USERNAME_TAKEN, OTHER_FAILURE, NOT_IMPLEMENTED };
    public enum ForgotPasswordResult { SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED };
    public enum ChangePasswordResult { SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED };
    public CreateResult createUser(LoginModel loginModel, String trackingId);
    public LoginResult login(LoginModel loginModel, String trackingId);
    public ForgotPasswordResult forgotPassword(LoginModel loginModel, String trackingId);
    public ChangePasswordResult changePassword(LoginModel loginModel, String trackingId);
}

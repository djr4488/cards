package com.djr.cards.auth.login;

import com.djr.cards.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/14/14
 * Time: 8:46 AM
 */
public class LoginResult {
    public enum ResultOptions { SUCCESS, FAILED_USER_OR_PASS, OTHER_FAILURE, NOT_IMPLEMENTED };
    public ResultOptions result;
    public User user;
}

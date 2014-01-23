package com.djr.cards.auth;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/11/14
 * Time: 8:38 PM
 */
public class AuthModel {
    private String userName;
    private String password;
    private String confirmPassword;
    private String alias;
    private String randomString;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "AuthModel{" +
                "userName='" + userName + '\'' +
                ", password=not displayed" +
                ", confirmPassword=not displayed"+
                ", alias='" + alias + '\'' +
                ", randomString='" + randomString + '\'' +
                '}';
    }
}

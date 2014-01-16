package com.djr.cards.login;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/11/14
 * Time: 8:38 PM
 */
public class LoginModel {
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "LoginModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", randomString='" + randomString + '\'' +
                '}';
    }
}

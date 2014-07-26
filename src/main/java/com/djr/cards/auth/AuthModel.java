package com.djr.cards.auth;

import java.io.Serializable;

/**
 * User: djr4488
 * Date: 1/11/14
 * Time: 8:38 PM
 */
public class AuthModel implements Serializable {
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AuthModel authModel = (AuthModel) o;

		if (alias != null ? !alias.equals(authModel.alias) : authModel.alias != null) {
			return false;
		}
		if (confirmPassword != null ? !confirmPassword
				.equals(authModel.confirmPassword) : authModel.confirmPassword != null) {
			return false;
		}
		if (password != null ? !password.equals(authModel.password) : authModel.password != null) {
			return false;
		}
		if (randomString != null ? !randomString.equals(authModel.randomString) : authModel.randomString != null) {
			return false;
		}
		if (userName != null ? !userName.equals(authModel.userName) : authModel.userName != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = userName != null ? userName.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
		result = 31 * result + (alias != null ? alias.hashCode() : 0);
		result = 31 * result + (randomString != null ? randomString.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "AuthModel{" +
				"userName='" + userName + '\'' +
				", password=not displayed" +
				", confirmPassword=not displayed" +
				", alias='" + alias + '\'' +
				", randomString='" + randomString + '\'' +
				'}';
	}
}

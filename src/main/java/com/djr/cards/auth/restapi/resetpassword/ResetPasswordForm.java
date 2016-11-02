package com.djr.cards.auth.restapi.resetpassword;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthForm;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dannyrucker
 *         Date: 3/14/14
 *         Time: 2:09 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ResetPasswordForm extends AuthForm {
	@XmlElement
	@NotNull
	public String securityCode;
	@XmlElement
	@NotNull
	public String password;
	@XmlElement
	@NotNull
	public String confirmPassword;

	public AuthModel getAuthModel() {
		AuthModel authModel = new AuthModel();
		authModel.setUserName(emailAddress);
		authModel.setPassword(password);
		authModel.setConfirmPassword(confirmPassword);
		authModel.setRandomString(securityCode);
		return authModel;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}

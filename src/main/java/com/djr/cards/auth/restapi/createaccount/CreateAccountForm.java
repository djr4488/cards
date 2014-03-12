package com.djr.cards.auth.restapi.createaccount;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthForm;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dannyrucker
 *         Date: 3/12/14
 *         Time: 5:04 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class CreateAccountForm extends AuthForm {
	@XmlElement
	@NotNull
	public String alias;
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
		authModel.setAlias(alias);
		return authModel;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}

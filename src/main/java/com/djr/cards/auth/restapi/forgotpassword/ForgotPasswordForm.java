package com.djr.cards.auth.restapi.forgotpassword;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.restapi.AuthForm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: djr4488
 * Date: 3/13/14
 * Time: 9:47 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ForgotPasswordForm extends AuthForm {
	public AuthModel getAuthModel() {
		AuthModel am = new AuthModel();
		am.setUserName(emailAddress);
		return am;
	}
}

package com.djr.cards.auth.restapi.login;

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
 *         Date: 2/21/14
 *         Time: 2:04 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestLoginForm extends AuthForm {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @NotNull
    public String password;

    public AuthModel getAuthModel() {
        AuthModel authModel = new AuthModel();
        authModel.setUserName(this.emailAddress);
        authModel.setPassword(this.password);
        return authModel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

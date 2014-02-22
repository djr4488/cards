package com.djr.cards.auth.restapi;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author dannyrucker
 *         Date: 2/21/14
 *         Time: 2:08 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class AuthForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @NotNull
    public String emailAddress;
}

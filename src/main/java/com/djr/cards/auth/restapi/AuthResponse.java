package com.djr.cards.auth.restapi;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 8:13 AM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    public AuthResponse() { }
    @XmlElement
    public String errorMsg;
    @XmlElement
    public String errorBold;
    @XmlElement
    public String nextLanding;
    @XmlElement
    public String token;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

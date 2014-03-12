package com.djr.cards.auth.restapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 8:13 AM
 */
@XmlRootElement
public class AuthResponse {
    @XmlElement
    public String errorMsg;
    @XmlElement
    public String errorBold;
    @XmlElement
    public String nextLanding;
    @XmlElement
    public String token;
}

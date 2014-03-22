package com.djr.cards.games.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: djr4488
 * Date: 3/22/2014
 * Time: 12:28 AM
 */
@XmlRootElement
public class GameResponse {
    @XmlElement
    public String nextLanding;
    @XmlElement
    public String errorMsg;
    @XmlElement
    public String errorBold;
}

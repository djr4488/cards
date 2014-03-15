package com.djr.cards.games.selector.restapi;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 11:01 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GameSelectionResponse {
    @XmlElement
    public List<String> gameOpts;
    @XmlElement
    public String nextLanding;
    @XmlElement
    public String errorMsg;
    @XmlElement
    public String errorBold;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

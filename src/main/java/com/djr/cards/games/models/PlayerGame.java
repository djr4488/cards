package com.djr.cards.games.models;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: djr4488
 * Date: 4/29/2014
 * Time: 8:34 PM
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PlayerGame {
    @XmlElement
    public String gameName;
    @XmlElement
    public String gameType;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

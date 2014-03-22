package com.djr.cards.games.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dannyrucker
 *         Date: 2/17/14
 *         Time: 3:27 PM
 */
@XmlRootElement
public class GameModel {
    private String gameName;
    private String gamePassword;
    private String gameType;

    @XmlElement
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @XmlElement
    public String getGamePassword() {
        return gamePassword;
    }

    public void setGamePassword(String gamePassword) {
        this.gamePassword = gamePassword;
    }

    @XmlElement
    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "gameName='" + gameName + '\'' +
                ", gamePassword='" + gamePassword + '\'' +
                ", gameType='" + gameType + '\'' +
                '}';
    }
}

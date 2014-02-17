package com.djr.cards.games.models;

/**
 * @author dannyrucker
 *         Date: 2/17/14
 *         Time: 3:27 PM
 */
public class GameModel {
    private String gameName;
    private String gamePassword;
    private String gameType;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePassword() {
        return gamePassword;
    }

    public void setGamePassword(String gamePassword) {
        this.gamePassword = gamePassword;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}

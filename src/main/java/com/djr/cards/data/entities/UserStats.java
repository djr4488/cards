package com.djr.cards.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:39 PM
 */
@Entity
@Table(name="user_stats")
public class UserStats {
    @Id
    private Long id;
    private Long userId;
    private String gameType;
    private Long wins;
    private Long totalPlayed;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getTotalPlayed() {
        return totalPlayed;
    }

    public void setTotalPlayed(Long totalPlayed) {
        this.totalPlayed = totalPlayed;
    }
}

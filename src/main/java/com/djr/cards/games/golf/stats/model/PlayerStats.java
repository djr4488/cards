package com.djr.cards.games.golf.stats.model;

import com.djr.cards.data.entities.UserStats;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:42 PM
 */
public class PlayerStats {
    private long wins;
    private long totalGames;
    private long rank;
    private String alias;

    public PlayerStats() { }

    public PlayerStats(UserStats userStats, int rank) {
        wins = userStats.wins;
        totalGames = userStats.totalPlayed;
        this.rank = rank;
        alias = userStats.user.alias;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(long totalGames) {
        this.totalGames = totalGames;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

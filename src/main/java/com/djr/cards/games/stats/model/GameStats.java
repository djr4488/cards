package com.djr.cards.games.stats.model;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:33 PM
 */
public class GameStats {
    private List<PlayerStats> top10;
    private PlayerStats userStats;

    public GameStats() { }

    public GameStats(List<PlayerStats> top10, PlayerStats userStats) {
        this.top10 = top10;
        this.userStats = userStats;
    }

    public List<PlayerStats> getTop10() {
        return top10;
    }

    public void setTop10(List<PlayerStats> top10) {
        this.top10 = top10;
    }

    public PlayerStats getUserStats() {
        return userStats;
    }

    public void setUserStats(PlayerStats userStats) {
        this.userStats = userStats;
    }
}

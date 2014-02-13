package com.djr.cards.games.stats;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.stats.model.GameStats;

/**
 * User: djr4488
 * Date: 2/13/14
 * Time: 8:43 AM
 */
public interface GameStatsService {
    public GameStats loadGameStats(String tracking, User user, String gameType);
}

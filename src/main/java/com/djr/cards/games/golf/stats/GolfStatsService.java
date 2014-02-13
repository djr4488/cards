package com.djr.cards.games.golf.stats;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.golf.stats.model.GolfStats;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:50 PM
 */
public interface GolfStatsService {
    public GolfStats loadGolfStats(String tracking, User user);
}

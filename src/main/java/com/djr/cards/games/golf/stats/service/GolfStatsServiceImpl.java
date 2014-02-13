package com.djr.cards.games.golf.stats.service;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.golf.stats.GolfStatsService;
import com.djr.cards.games.golf.stats.model.GolfStats;
import com.djr.cards.games.stats.UserStatsDAO;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:50 PM
 */
public class GolfStatsServiceImpl implements GolfStatsService {
    @Inject
    private Logger logger;
    @Inject
    private UserStatsDAO userStatsDao;

    @Override
    public GolfStats loadGolfStats(String tracking, User user) {
        //TODO - the following
        //load stats from data base
        //sort stats
        //get the top 10 players
        //get the user stats and rank
        //put into GolfStats instance
        //return GolfStats instance
        return null;
    }
}

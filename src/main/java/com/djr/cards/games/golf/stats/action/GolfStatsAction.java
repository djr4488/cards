package com.djr.cards.games.golf.stats.action;

import com.djr.cards.BaseAction;
import com.djr.cards.games.golf.stats.model.GolfStats;
import com.opensymphony.xwork2.ModelDriven;
import org.slf4j.Logger;
import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:29 PM
 */
public class GolfStatsAction extends BaseAction implements ModelDriven<GolfStats> {
    @Inject
    private Logger logger;
    private GolfStats golfStats = new GolfStats();

    @Override
    public GolfStats getModel() {
        return golfStats;
    }

    public String loadPlayStats() {
        //load user stats from database
        //sort user stats
        //get top 10 user stats
        //get players user stats
        return "success";
    }
}

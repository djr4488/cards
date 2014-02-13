package com.djr.cards.games.golf.stats.action;

import com.djr.cards.BaseAction;
import com.djr.cards.data.entities.User;
import com.djr.cards.games.golf.stats.GolfStatsService;
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
    @Inject
    private GolfStatsService golfStatsSvc;

    private GolfStats golfStats = new GolfStats();

    @Override
    public GolfStats getModel() {
        return golfStats;
    }

    public String loadPlayStats() {
        logger.info("loadPlayStats() - tracking:{}", getSessionAttribute("tracking"));
        golfStats = golfStatsSvc.loadGolfStats(getSessionAttribute("tracking"),
                (User)getSession().getAttribute("user"));
        if (golfStats == null) {
            getSession().setAttribute("msgbold", "error.golf.stats.bold");
            getSession().setAttribute("msgtext", "error.golf.stats.text");
            return "error";
        }
        return "success";
    }
}

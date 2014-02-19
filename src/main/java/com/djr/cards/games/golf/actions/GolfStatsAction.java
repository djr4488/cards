package com.djr.cards.games.golf.actions;

import com.djr.cards.BaseAction;
import com.djr.cards.data.entities.User;
import com.djr.cards.games.stats.GameStatsService;
import com.djr.cards.games.stats.model.GameStats;
import com.opensymphony.xwork2.ModelDriven;
import org.slf4j.Logger;
import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:29 PM
 */
public class GolfStatsAction extends BaseAction implements ModelDriven<GameStats> {
    @Inject
    private Logger logger;
    @Inject
    private GameStatsService gameStatsSvc;

    private GameStats gameStats = new GameStats();

    @Override
    public GameStats getModel() {
        return gameStats;
    }

    public String getGameNameLabel() {
        logger.debug("getGameNameLabel() - {}", getText("game.create.name"));
        return getText("game.create.name");
    }

    public String getGamePasswordLabel() {
        logger.debug("getGamePasswordLabel() - {}", getText("game.create.password"));
        return getText("game.create.password");
    }

    public String loadPlayStats() {
        logger.info("loadPlayStats() - tracking:{}", getSessionAttribute("tracking"));
        gameStats = gameStatsSvc.loadGameStats(getSessionAttribute("tracking"),
                (User)getSession().getAttribute("user"), "Golf");
        if (gameStats == null) {
            getSession().setAttribute("msgbold", "error.golf.stats.bold");
            getSession().setAttribute("msgtext", "error.golf.stats.text");
            return "error";
        }
        gameStats.setGameAction("golfLanding");
        logger.debug("loadPlayStats() - gameStats:{}", gameStats);
        removeAndSetSessionAttribute("statsTitle", getText("golf.stats.title"));
        removeAndSetSessionAttribute("gameType", "Golf");
        return "success";
    }
}

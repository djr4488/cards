package com.djr.cards.games.restapi;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.BaseGameController;
import com.djr.cards.games.GameService;
import com.djr.cards.games.models.CreateGameResult;
import com.djr.cards.games.models.GameModel;
import com.djr.cards.games.models.GameResponse;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/22/2014
 * Time: 12:23 AM
 */
public abstract class GameController extends BaseGameController {
    @Inject
    private Logger log;
    @Inject
    private GameService gameService;

    @POST
    @Path("/create/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponse createGame(GameModel gameModel, @Context HttpServletRequest request) {
        String tracking = (String)getSession(request).getAttribute("tracking");
        log.info("createGame() - gameModel:{}, tracking:{}", gameModel, tracking);
        GameResponse gameResponse = new GameResponse();
        User user = (User)getSession(request).getAttribute("user");
        CreateGameResult createGameResult = gameService.createGame(gameModel, user, tracking);
        if (createGameResult == null || createGameResult.actionLanding == null ||
                createGameResult.actionLanding.trim().equals("") ||
                createGameResult.actionLanding.equalsIgnoreCase("error")) {
            gameResponse.errorMsg = "Something went wrong when manipulating the time vortex!";
            gameResponse.errorBold = "Be afraid!";
            return gameResponse;
        }
        if (createGameResult.actionLanding.equals("inlineCreate")) {
            gameResponse.errorMsg = "Somebody beat you to it and already took this game name!";
            gameResponse.errorBold = "Oops!  They took over the machine!";
            return gameResponse;
        }
        if (createGameResult.actionLanding.equals("inlineTriesExceeded")) {
            gameResponse.errorMsg = "Couldn't create the game at this time.";
            gameResponse.errorBold = "I tried, really I did!";
            return gameResponse;
        }
        removeAndSetSessionAttribute("gameid", createGameResult.game.getId(), request);
        gameResponse.nextLanding = "golfGame";
        return gameResponse;
    }
}

package com.djr.cards.games.restapi;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.games.BaseGameController;
import com.djr.cards.games.GameService;
import com.djr.cards.games.exceptions.PlayGameException;
import com.djr.cards.games.models.*;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
        gameResponse.nextLanding = createGameResult.actionLanding;
        return gameResponse;
    }

    private String joinGameResultInlineError(JoinGameResult joinGameResult) {
        if (joinGameResult.landingAction.equals("inlinePassword")) {
            return "That was the wrong password for the game.";
        }
        if (joinGameResult.landingAction.equals("inlineStarted")) {
            return "Just a bit slow since the game already started.";
        }
        if (joinGameResult.landingAction.equals("inlineTriesExceeded")) {
            return "I couldn't seem to get you into the game, I did try though.";
        }
        if (joinGameResult.landingAction.equals("inlineAlreadyJoined")) {
            return "It is a most odd conundrum, you seem to be in this game already.";
        }
        if (joinGameResult.landingAction.equals("inlineNotFound")) {
            return "I didn't find this game, you sure this is the game?";
        }
        return null;
    }

    @POST
    @Path("/join/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponse joinGame(GameModel gameModel, @Context HttpServletRequest request) {
        String tracking = (String)getSession(request).getAttribute("tracking");
        log.info("joinGame() - gameModel:{}, tracking:{}", gameModel, tracking);
        GameResponse gameResponse = new GameResponse();
        User user = (User)getSession(request).getAttribute("user");
        JoinGameResult joinGameResult = gameService.joinGame(gameModel, user, tracking);
        String inlineError = joinGameResultInlineError(joinGameResult);
        if (inlineError != null) {
            gameResponse.errorBold = "Whoa there!";
            gameResponse.errorMsg = inlineError;
            return gameResponse;
        }
        gameResponse.nextLanding = joinGameResult.landingAction;
        return gameResponse;
    }

    @GET
    @Path("/playlist/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponse getPlayGameList(GameModel gameModel, @Context HttpServletRequest request) {
        String tracking = (String)getSession(request).getAttribute("tracking");
        log.info("getPlayGameList() - gameModel:{}, tracking:{}", gameModel, tracking);
        GameResponse gameResponse = new GameResponse();
        User user = (User)getSession(request).getAttribute("user");
        try {
            List<PlayerGame> gamesPlayerIsIn = gameService.getGamesPlayerIsIn(gameModel.getGameType(), user, tracking);
            if (gamesPlayerIsIn == null || gamesPlayerIsIn.size() == 0) {
                //not in games
                gameResponse.errorBold = "No games you are in!";
                gameResponse.errorMsg = "Join games you must.";
            } else {
                gameResponse.gamesPlayerIsIn = gamesPlayerIsIn;
            }
        } catch (Exception ex) {
            //error finding games
            gameResponse.errorBold = "Slow down, Sparky!";
            gameResponse.errorMsg = "It would seem I couldn't load any games.  Can you try again?";
        }
        return gameResponse;
    }

    @POST
    @Path("/play/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponse playGame(GameModel gameModel, @Context HttpServletRequest request) {
        String tracking = (String)getSession(request).getAttribute("tracking");
        log.info("playGame() = gameModel:{}, tracking:{}", gameModel, tracking);
        GameResponse gameResponse = new GameResponse();
        User user = (User)getSession(request).getAttribute("user");
        try {
            PlayGameResult playGameResult = gameService.playGame(gameModel, user, tracking);
            if (playGameResult.landingAction.equals("inlineGameNotFoundError")) {
                gameResponse.errorBold = "Most interesting result!";
                gameResponse.errorMsg = "It would appear that the game you are seeking to play cannot be found.";
            } else if (playGameResult.landingAction.equals("inlineNotInGameError")) {
                gameResponse.errorBold = "Sneaky!";
                gameResponse.errorMsg = "It would seem that you are not actually in this game?  Perhaps you could join it.";
            } else {
                gameResponse.nextLanding = playGameResult.landingAction;
            }
        } catch (PlayGameException pgEx) {
            log.error("playGame() - error occurred while attempting to play game", pgEx);
            gameResponse.errorBold = "WHOA!";
            gameResponse.errorMsg = "I think someone tried science and failed.";
        }
        return gameResponse;
    }
}

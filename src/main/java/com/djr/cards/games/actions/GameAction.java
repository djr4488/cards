package com.djr.cards.games.actions;

import com.djr.cards.BaseAction;
import com.djr.cards.data.entities.User;
import com.djr.cards.games.GameService;
import com.djr.cards.games.models.CreateGameResult;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.models.GameModel;
import com.djr.cards.games.models.JoinGameResult;
import com.opensymphony.xwork2.ModelDriven;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author dannyrucker
 *         Date: 2/17/14
 *         Time: 3:26 PM
 */
public class GameAction extends BaseAction implements ModelDriven<GameModel> {
    @Inject
    private Logger logger;
    @Inject
    private GameService gameService;
    private GameModel gameModel = new GameModel();

    @Override
    public GameModel getModel() {
        return gameModel;
    }

    public void validate() {
        logger.debug("validate() - tracking:{}, gameType:{}, gameName:{}, gamePassword:{}",
                getSessionAttribute("tracking"), gameModel.getGameType(), gameModel.getGameName(),
                gameModel.getGamePassword());
        if (gameModel.getGameName() == null || gameModel.getGameName().trim().equals("")) {
            addActionError("error.game.name.required");
        }
    }

    private boolean createGameResultHasInlineError(CreateGameResult createGameResult) {
        if (createGameResult.actionLanding.equals("inlineCreate")) {
            addActionError("error.game.name.exists");
            return true;
        }
        if (createGameResult.actionLanding.equals("inlineTriesExceeded")) {
            addActionError("error.game.tries.exceeded");
            return true;
        }
        return false;
    }

    public String createGame() {
        logger.info("createGame() - tracking:{}, gameType:{}, gameName:{}, gamePassword:{}",
                getSessionAttribute("tracking"), gameModel.getGameType(), gameModel.getGameName(),
                gameModel.getGamePassword());
        User user = (User)getSession().getAttribute("user");
        CreateGameResult createGameResult;
        createGameResult = gameService.createGame(gameModel, user, getSessionAttribute("tracking"));
        if (createGameResult == null || createGameResult.actionLanding == null ||
                createGameResult.actionLanding.trim().equals("") ||
                createGameResult.actionLanding.equalsIgnoreCase("error")) {
            removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
            removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
            return "error";
        }
        if (createGameResultHasInlineError(createGameResult)) {
            return "inline";
        }
        removeAndSetSessionAttribute("gameid", createGameResult.game.getId());
        logger.info("createGame() - forward to {}", createGameResult.actionLanding);
        return createGameResult.actionLanding;
    }

    private boolean joinGameResultHasInlineError(JoinGameResult joinGameResult) {
        if (joinGameResult.landingAction.equals("inlinePassword")) {
            addActionError("error.game.wrong.password");
            return true;
        }
        if (joinGameResult.landingAction.equals("inlineStarted")) {
            addActionError("error.game.started");
            return true;
        }
        if (joinGameResult.landingAction.equals("inlineTriesExceeded")) {
            addActionError("error.game.tries.exceeded");
            return true;
        }
        return false;
    }

    public String joinWaitingGame() {
        logger.info("joinGame() - tracking:{}, gameType:{}, gameName:{}, gamePassword:{}",
                getSessionAttribute("tracking"), gameModel.getGameType(), gameModel.getGameName(),
                gameModel.getGamePassword());
        User user = (User)getSession().getAttribute("user");
        JoinGameResult joinGameResult = gameService.joinGame(gameModel, user, getSessionAttribute("tracking"));
        if (joinGameResult == null || joinGameResult.landingAction == null ||
                joinGameResult.landingAction.trim().equals("") ||
                joinGameResult.landingAction.trim().equalsIgnoreCase("error")) {
            removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
            removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
            return "error";
        }
        if (joinGameResultHasInlineError(joinGameResult)) {
            return "inline";
        }
        logger.debug("joinWaitingGame() - with landing action {}", joinGameResult.landingAction);
        removeAndSetSessionAttribute("gameid", joinGameResult.game);
        return joinGameResult.landingAction;
    }

    public String playGame() {
        logger.info("playGame() - tracking:{}, gameType:{}, gameName:{}",
                getSessionAttribute("tracking"), gameModel.getGameType(), gameModel.getGameName());
        removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
        return "error";
    }
}

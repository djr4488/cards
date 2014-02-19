package com.djr.cards.games.actions;

import com.djr.cards.BaseAction;
import com.djr.cards.data.entities.User;
import com.djr.cards.games.GameService;
import com.djr.cards.games.daos.CreateGameResult;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.models.GameModel;
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

    public String createGame() {
        logger.info("createGame() - tracking:{}, gameType:{}, gameName:{}, gamePassword:{}",
                getSessionAttribute("tracking"), gameModel.getGameType(), gameModel.getGameName(),
                gameModel.getGamePassword());
        User user = (User)getSession().getAttribute("user");
        CreateGameResult createGameResult = null;
        try {
            createGameResult = gameService.createGame(gameModel, user, getSessionAttribute("tracking"));
        } catch (CreateGameException cgEx) {
            addActionError("error.game.name.exists");
            return "inline";
        }
        if (createGameResult == null || createGameResult.actionLanding == null ||
                createGameResult.actionLanding.trim().equals("") ||
                createGameResult.actionLanding.equalsIgnoreCase("error")) {
            removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
            removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
            return "error";
        }
        logger.info("createGame() - forward to {}", createGameResult.actionLanding);
        return createGameResult.actionLanding;
    }

    public String joinWaitingGame() {
        removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
        return "error";
    }

    public String playGame() {
        removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
        return "error";
    }
}

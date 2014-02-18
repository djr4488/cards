package com.djr.cards.games.actions;

import com.djr.cards.BaseAction;
import com.djr.cards.data.entities.User;
import com.djr.cards.games.GameService;
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
        String action = null;
        try {
            action = gameService.createGame(gameModel.getGameType(), gameModel.getGameName(),
                    gameModel.getGamePassword(), user, getSessionAttribute("tracking"));
        } catch (CreateGameException cgEx) {
            addActionError("error.game.name.exists");
            return "inline";
        }
        if (action == null || action.trim().equals("") || action.equalsIgnoreCase("error")) {
            removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
            removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
            return "error";
        }
        logger.info("createGame() - forward to {}", action);
        return action;
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

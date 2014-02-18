package com.djr.cards.games.actions;

import com.djr.cards.BaseAction;
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
    private GameModel gameModel = new GameModel();

    @Override
    public GameModel getModel() {
        return gameModel;
    }

    public String createGame() {
        logger.info("createGame() - gameType:{}, gameName:{}, gamePassword:{}", gameModel.getGameType(),
                gameModel.getGameName(), gameModel.getGamePassword());
        removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
        return "error";
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

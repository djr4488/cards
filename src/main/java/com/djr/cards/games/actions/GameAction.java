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
        removeAndSetSessionAttribute("msgbold", "error.create.game.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.create.game.unimplemented.text");
        return "error";
    }
}

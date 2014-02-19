package com.djr.cards.games.services;

import com.djr.cards.audit.AuditService;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;
import com.djr.cards.games.GameService;
import com.djr.cards.games.daos.CreateGameResult;
import com.djr.cards.games.daos.GameDAO;
import com.djr.cards.games.daos.PlayerDAO;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.exceptions.JoinGameException;
import com.djr.cards.games.exceptions.PlayGameException;
import com.djr.cards.games.models.GameModel;
import com.djr.cards.games.selector.SelectorDAO;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:32 AM
 */
public class GameServiceImpl implements GameService {
    @Inject
    private Logger logger;
    @Inject
    private GameDAO gameDao;
    @Inject
    private PlayerDAO playerDao;
    @Inject
    private SelectorDAO selectorDao;
    @Inject
    private AuditService auditService;

    @Override
    public CreateGameResult createGame(GameModel gameModel, User user, String tracking)
    throws CreateGameException {
        logger.debug("createGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        try {
            Game game = gameDao.createGame(gameModel, user, tracking);
            auditService.writeAudit(auditService.getAuditLog(tracking, "createGame()", gameModel.toString(),
                    Calendar.getInstance()));
            playerDao.createPlayer(game, user, tracking);
            CreateGameResult result = new CreateGameResult();
            result.game = game;
            result.actionLanding = selectorDao.findGameSelection(gameModel.getGameType()).gameAction;
            return result;
        } catch (CreateGameException cgEx) {
            logger.error("createGame() - failed with exception ", cgEx);
        }
        return null;
    }

    @Override
    public String joinGame(GameModel gameModel, User user, String tracking) throws JoinGameException {
        logger.debug("joinGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        return null;
    }

    @Override
    public String playGame(GameModel gameModel, User user, String tracking) throws PlayGameException {
        logger.debug("playGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        return null;
    }
}

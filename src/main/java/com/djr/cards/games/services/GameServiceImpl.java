package com.djr.cards.games.services;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.GameService;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.exceptions.JoinGameException;
import com.djr.cards.games.exceptions.PlayGameException;
import com.djr.cards.games.models.GameModel;
import com.opensymphony.xwork2.inject.Inject;
import org.slf4j.Logger;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:32 AM
 */
public class GameServiceImpl implements GameService {
    @Inject
    private Logger logger;

    @Override
    public String createGame(GameModel gameModel, User user, String tracking)
    throws CreateGameException {
        logger.debug("createGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
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

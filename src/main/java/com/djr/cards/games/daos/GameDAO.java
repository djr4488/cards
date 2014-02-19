package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.models.GameModel;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 7:02 PM
 */
public interface GameDAO {
    public Game findGame(GameModel gameModel, User user, String tracking);
    public List<Game> findGamesPlaying(GameModel gameModel, User user, String tracking);
    public Game createGame(GameModel gameModel, User user, String tracking) throws CreateGameException;
    public void updateGameStatus(Game game, boolean isWaiting, String tracking);
}

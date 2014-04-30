package com.djr.cards.games;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.games.models.*;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.exceptions.JoinGameException;
import com.djr.cards.games.exceptions.PlayGameException;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:32 AM
 */
public interface GameService {
    /**
     * This method will attempt to create a game name for a user.  Games are considered 'unique' by
     * the combination of gameType, gameName, and alias.  If a user has already created a game with
     * the particular name and game type, this will throw an exception
     *
     * @param gameModel GameModel
     * @param user User
     * @param tracking String
     * @return CreateGameResult Is pertinent information to game creation and landing action
     */
    public CreateGameResult createGame(GameModel gameModel, User user, String tracking);

    /**
     * This method will attempt place the User into a game which is not yet started.
     *
     * @param gameModel GameModel
     * @param user User
     * @param tracking String
     * @return String Is the forwarding action to take
     */
    public JoinGameResult joinGame(GameModel gameModel, User user, String tracking);

    /**
     * This method will attempt to allow the user to play/view a game which he is a part of
     *
     * @param gameModel GameModel
     * @param user User
     * @param tracking String
     * @return PlayGameResult Is the forwarding action to take
     * @throws PlayGameException
     */
    public PlayGameResult playGame(GameModel gameModel, User user, String tracking)
    throws PlayGameException;

    /**
     * This methods looks up the games the player is in for the specified game type
     *
     * @param gameType
     * @param user
     * @param tracking
     * @return
     */
    public List<PlayerGame> getGamesPlayerIsIn(String gameType, User user, String tracking);
}

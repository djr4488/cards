package com.djr.cards.games;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.exceptions.CreateGameException;

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
     * @param gameType String
     * @param gameName String
     * @param gamePassword String
     * @param user User
     * @param tracking String
     * @return String
     * @throws CreateGameException
     */
    public String createGame(String gameType, String gameName, String gamePassword, User user, String tracking)
    throws CreateGameException;
}

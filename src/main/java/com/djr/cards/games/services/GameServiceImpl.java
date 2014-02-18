package com.djr.cards.games.services;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.GameService;
import com.djr.cards.games.exceptions.CreateGameException;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:32 AM
 */
public class GameServiceImpl implements GameService {
    @Override
    public String createGame(String gameType, String gameName, String gamePassword, User user, String tracking)
    throws CreateGameException {
        return null;
    }
}

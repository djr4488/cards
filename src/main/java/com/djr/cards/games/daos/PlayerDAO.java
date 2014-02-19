package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 7:03 PM
 */
public interface PlayerDAO {
    public Player createPlayer(Game game, User user, String tracking);
}

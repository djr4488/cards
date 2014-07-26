package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 7:03 PM
 */
public interface PlayerDAO {
	public Player createPlayer(Game game, User user, String tracking);

	public boolean isUserAPlayer(Game game, User user, String tracking);

	public List<Game> findGamesPlayerIsIn(String gameType, User user, String tracking);
}

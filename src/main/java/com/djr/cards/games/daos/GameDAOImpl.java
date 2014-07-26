package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.models.GameModel;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 7:02 PM
 */
@Stateless
public class GameDAOImpl implements GameDAO {
	@Inject
	private Logger logger;
	@PersistenceContext
	private EntityManager em;

	@Override
	public Game findGame(GameModel gameModel, User user, String tracking) {
		try {
			TypedQuery<Game> query = em.createNamedQuery("findGameByNameAndType", Game.class);
			query.setParameter("gameName", gameModel.getGameName());
			query.setParameter("gameType", gameModel.getGameType());
			return query.getSingleResult();
		} catch (NoResultException nrEx) {
			return null;
		}
	}

	@Override
	public List<Game> findGamesPlaying(GameModel gameModel, User user, String tracking) {
		logger.debug("findGamesPlaying() - gameModel:{}, user:{}, tracking:{}", gameModel, user, tracking);
		try {
			TypedQuery<Game> query = em.createNamedQuery("findGamesUserPlaying", Game.class);
			query.setParameter("gameName", gameModel.getGameName());
			query.setParameter("gameType", gameModel.getGameType());
			query.setParameter("user", user);
			return query.getResultList();
		} catch (NoResultException nrEx) {
			logger.debug("findGamesPlaying() - no games found for {}", user.alias);
		}
		return null;
	}

	@Override
	public Game createGame(GameModel gameModel, User user, String tracking) throws CreateGameException {
		logger.debug("createGame() - gameModel:{}, user:{}, tracking:{}", gameModel, user.alias, tracking);
		if (findGame(gameModel, user, tracking) == null) {
			Game game = new Game();
			game.gameName = gameModel.getGameName();
			game.gamePassword = gameModel.getGamePassword();
			game.gameType = gameModel.getGameType();
			game.user = user;
			game.lastUpdated = Calendar.getInstance();
			game.isWaitingForPlayers = new Boolean(false);
			try {
				em.persist(game);
				return game;
			} catch (OptimisticLockException olEx) {
				logger.debug("createGame() - {} was just created before this instance.", gameModel.getGameName());
				throw new CreateGameException("Game already exists - Optimistic Lock Exception");
			}
		}
		logger.debug("createGame() - {} already exists", gameModel.getGameName());
		return null;
	}

	@Override
	public void updateGameStatus(Game game, boolean isWaiting, String tracking) {
		logger.debug("updateGameStatus() - game:{}, isWaiting:{}, tracking:{}", game, isWaiting, tracking);
		Game managed = em.find(Game.class, game.getId());
		game.lastUpdated = Calendar.getInstance();
		game.isWaitingForPlayers = true;
		em.merge(game);
	}
}

package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 7:03 PM
 */
@Stateless
public class PlayerDAOImpl implements PlayerDAO {
    @Inject
    private Logger logger;
    @PersistenceContext
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Player createPlayer(Game game, User user, String tracking) {
        logger.debug("createPlayer() - game:{}, user:{}", game, user.alias);
        Player player = new Player();
        player.gameId = game;
        player.user = user;
        em.persist(player);
        return player;
    }

    public boolean isUserAPlayer(Game game, User user, String tracking) {
        logger.debug("isUserAPlayer() - game:{}, user:{}, tracking:{}", game, user.alias, tracking);
        TypedQuery<Player> query = em.createNamedQuery("findUserAsPlayer", Player.class);
        query.setParameter("user", user);
        query.setParameter("game", game);
        try {
            return query.getSingleResult() != null;
        } catch (NoResultException nrEx) {
            return false;
        }
    }

    @Override
    public List<Game> findGamesPlayerIsIn(String gameType, User user, String tracking) {
        logger.debug("findGamesPlayerIsIn() - gameType:{}, user:{}, tracking:{}", gameType, user.alias, tracking);
        TypedQuery<Game> query = em.createNamedQuery("findGamesPlayerIsIn", Game.class);
        query.setParameter("user", user);
        query.setParameter("gameType", gameType);
        try {
            return query.getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}

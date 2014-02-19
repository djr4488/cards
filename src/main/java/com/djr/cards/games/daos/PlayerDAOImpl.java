package com.djr.cards.games.daos;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public Player createPlayer(Game game, User user, String tracking) {
        logger.debug("createPlayer() - game:{}, user:{}", game, user.alias);
        Player player = new Player();
        player.gameId = game;
        player.user = user;
        em.persist(player);
        return null;
    }
}
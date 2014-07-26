package com.djr.cards.games.stats.dao;

import com.djr.cards.games.stats.UserStatsDAO;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/3/14
 * Time: 10:16 PM
 */
@Stateless
public class UserStatsDAOImpl implements UserStatsDAO {
	@Inject
	private org.slf4j.Logger logger;
	@PersistenceContext
	private javax.persistence.EntityManager em;

	@Override
	public UserStats findStatsByUser(User user, String gameType, String tracking) {
		logger.debug("findStatsByUser() - user:{}, gameType:{}, tracking:{}", user, gameType, tracking);
		UserStats userStats = null;
		try {
			TypedQuery<UserStats> query = em.createNamedQuery("findUserStatsByUser", UserStats.class);
			query.setParameter("user", user);
			query.setParameter("gameType", gameType);
			userStats = query.getSingleResult();
		} catch (NoResultException nrEx) {
			userStats = new UserStats();
			userStats.totalPlayed = 0L;
			userStats.gameType = gameType;
			userStats.user = user;
			userStats.wins = 0L;
			em.persist(userStats);
		}
		logger.debug("findStatsByUser() - userStats:{}", userStats);
		return userStats;
	}

	@Override
	public List<UserStats> loadStatistics(String gameType, String tracking) {
		logger.debug("loadStatistics() - gameType:{}, tracking:{}", gameType, tracking);
		try {
			TypedQuery<UserStats> query = em.createNamedQuery("findUserStats", UserStats.class);
			List<UserStats> unorderedStats = query.getResultList();
			return unorderedStats;
		} catch (NoResultException nrEx) {
			//what to do here? findStatsByUser should have created something before here..
			//for now log the error and return null
			logger.error("loadStatistics() - ", nrEx);
		}
		return null;
	}
}

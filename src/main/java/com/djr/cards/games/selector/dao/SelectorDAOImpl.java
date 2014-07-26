package com.djr.cards.games.selector.dao;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.selector.SelectorDAO;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author djr4488
 *         Date: 2/5/14
 *         Time: 2:37 PM
 */
@Stateless
public class SelectorDAOImpl implements SelectorDAO {
	@Inject
	private Logger logger;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<GameSelection> findGameSelectionOptions() {
		logger.debug("findGameSelectionOptions()");
		try {
			TypedQuery<GameSelection> query = em.createNamedQuery("findAllGameOptions", GameSelection.class);
			return query.getResultList();
		} catch (NoResultException nrEx) {
			//what to do here?  probably nothing, means I haven't enabled any games
			logger.error("findGameSelectionOptions() - exception:", nrEx);
		}
		return null;
	}

	@Override
	public GameSelection findGameSelection(String gameType) {
		logger.debug("findGameSelection() - gameType:{}", gameType);
		try {
			TypedQuery<GameSelection> query = em.createNamedQuery("findGameSelectionByGameType", GameSelection.class);
			query.setParameter("gameType", gameType);
			return query.getSingleResult();
		} catch (NoResultException nrEx) {
			//i haven't enabled the game yet but I'll log the error anyway
			logger.error("findGameSelection() - exception:", nrEx);
		}
		return null;
	}
}

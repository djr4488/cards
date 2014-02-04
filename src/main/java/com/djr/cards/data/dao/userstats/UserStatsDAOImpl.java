package com.djr.cards.data.dao.userstats;

import com.djr.cards.data.dao.UserStatsDAO;
import com.djr.cards.data.entities.UserStats;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
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
    private javax.persistence.EntityManager entityManager;

    @Override
    public UserStats findStatsByEmailAddress(String emailAddress) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UserStats> loadStatistics(String emailAddress) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

package com.djr.cards.auth.dao;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.auth.dao.UserDAO;
import com.djr.cards.data.entities.User;
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

/**
 * User: djr4488
 * Date: 1/23/14
 * Time: 11:22 PM
 */
@Stateless
public class UserDAOImpl implements UserDAO {
    @Inject
    private Logger logger;
    @PersistenceContext
    private EntityManager em;

    public UserDAOImpl() { }

    private FindUserResult createFindUserResult(User user, boolean created) {
        FindUserResult findUserResult = new FindUserResult();
        findUserResult.user = user;
        findUserResult.created = created;
        return findUserResult;
    }

	@TransactionAttribute (TransactionAttributeType.REQUIRED)
    public FindUserResult findOrCreateUser(AuthModel authModel, String trackingId) {
        logger.debug("findOrCreateUser() - authModel:{}, trackingId:{}", authModel, trackingId);
        try {
            TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
            query.setParameter("emailAddress", authModel.getUserName());
            return createFindUserResult(query.getSingleResult(), false);
        } catch (NoResultException nrEx) {
            User user = new User(authModel);
            user.createdDate = Calendar.getInstance();
            try {
                em.persist(user);
                return createFindUserResult(user, true);
            } catch (Exception ex) {
                logger.error("findOrCreateUser() - trackingId:{}, exception:{}", trackingId, ex);
            }
        }
        return null;
    }

    public User findUser(AuthModel authModel, String trackingId) {
        logger.debug("findUser() - authModel:{}, trackingId:{}", authModel, trackingId);
        try {
            TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
            query.setParameter("emailAddress", authModel.getUserName());
            return query.getSingleResult();
        } catch (NoResultException nrEx) {
            logger.debug("findUser() - No user found.");
            return null;
        }
    }

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User updateUser(User user, String trackingId) {
        logger.debug("updateUser() - user:{}, trackingId:{}", user, trackingId);
        try {
            em.merge(user);
        } catch (Exception ex) {
            logger.error("updateUser() exception:", ex);
        }
        return user;
    }
}

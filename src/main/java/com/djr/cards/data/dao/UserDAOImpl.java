package com.djr.cards.data.dao;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.data.entities.User;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 1/23/14
 * Time: 11:22 PM
 */
public class UserDAOImpl implements UserDAO {
    @Inject
    private Logger logger;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction userTx;

    public UserDAOImpl() { }

    private FindUserResult createFindUserResult(User user, boolean created) {
        FindUserResult findUserResult = new FindUserResult();
        findUserResult.user = user;
        findUserResult.created = created;
        return findUserResult;
    }

    //may consider moving these two methods out of auth... but really this is the only time I'm
    //going to create/find user... so I'm not sure it really matters
    public FindUserResult findOrCreateUser(AuthModel authModel, String trackingId) {
        logger.debug("findOrCreateUser() - authModel:{}, trackingId:{}", authModel, trackingId);
        try {
            TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
            query.setParameter("userName", authModel.getUserName());
            return createFindUserResult(query.getSingleResult(), false);
        } catch (NoResultException nrEx) {
            User user = new User(authModel);
            user.createdDate = Calendar.getInstance();
            try {
                userTx.begin();
                em.persist(user);
                userTx.commit();
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
            query.setParameter("userName", authModel.getUserName());
            return query.getSingleResult();
        } catch (NoResultException nrEx) {
            logger.debug("findUser() - No user found.");
            return null;
        }
    }

    public User updateUser(User user, String trackingId) {
        logger.debug("updateUser() - user:{}, trackingId:{}", user, trackingId);
        try {
            userTx.begin();
            em.merge(user);
            userTx.commit();
        } catch (Exception ex) {
            logger.error("updateUser() exception:", ex);
        }
        return user;
    }
}

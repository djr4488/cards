package com.djr.cards.auth;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.login.LoginResult;
import com.djr.cards.email.EmailService;
import com.djr.cards.entities.User;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.util.Calendar;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:07 PM
 */
public class AuthServiceImpl implements AuthService {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditService;
    @Inject
    private EmailService emailService;
    @Inject
    private Session session;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction userTx;


    class FindUserResult {
        public User user;
        public boolean created;
    }

    private FindUserResult createFindUserResult(User user, boolean created) {
        FindUserResult findUserResult = new FindUserResult();
        findUserResult.user = user;
        findUserResult.created = created;
        return findUserResult;
    }

    private FindUserResult findOrCreateUser(AuthModel authModel, String trackingId) {
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

    private User findUser(AuthModel authModel, String trackingId) {
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

    @Override
    public CreateResult createUser(AuthModel authModel, String trackingId) {
        logger.debug("createUser - authModel:{}, trackingId:{}", authModel, trackingId);
        FindUserResult findUserResult = findOrCreateUser(authModel, trackingId);
        if (findUserResult == null) {
            return CreateResult.OTHER_FAILURE;
        } else if (!findUserResult.created) {
            return CreateResult.USERNAME_TAKEN;
        }
        return CreateResult.CREATED;
    }

    @Override
    public LoginResult login(AuthModel authModel, String trackingId) {
        logger.debug("auth() - authModel:{}, trackingId:{}", authModel, trackingId);
        User user = findUser(authModel, trackingId);
        LoginResult loginResult = new LoginResult();
        if (user == null || !user.hashedPassword.equals(authModel.getPassword())) {
            auditService.writeAudit(auditService.getAuditLog(trackingId, "AuthService.auth()",
                    authModel.toString(), Calendar.getInstance()));
            loginResult.result = LoginResult.ResultOptions.FAILED_USER_OR_PASS;
            loginResult.user = null;
            return loginResult;
        }
        loginResult.result = LoginResult.ResultOptions.SUCCESS;
        loginResult.user = user;
        return loginResult;
    }

    @Override
    public ForgotPasswordResult forgotPassword(AuthModel authModel, String trackingId) {
        logger.debug("forgotPassword() - authModel:{}, trackingId:{}", authModel, trackingId);
        auditService.writeAudit(auditService.getAuditLog(trackingId, "forgotPassword()", authModel.toString(),
                Calendar.getInstance()));
        authModel.setUserName(authModel.getUserName());
        User user = findUser(authModel, trackingId);
        if (user == null) {
            return ForgotPasswordResult.NOT_FOUND;
        } else {
            Random r = new Random();
            Integer code = r.nextInt(50000);
            String emailBody = "Here to help, lets make it so you can change your password!\nJust use the code below " +
                    "to change your password.  If you didn't initiate this, don't change your password.\n\n" +
                    "Code -> " + code + ".";
            String subject = "Cards - Forgot Password Service";
            if (emailService.sendEmail(user.userName, user.alias, subject, emailBody, session)) {
                user.changePasswordProof = code.toString();
                try {
                    userTx.begin();
                    em.merge(user);
                    userTx.commit();
                } catch (Exception ex) {
                    logger.error("forgotPassword() exception:", ex);
                }
                return ForgotPasswordResult.SUCCESS;
            }
        }
        return ForgotPasswordResult.OTHER_FAILURE;
    }

    @Override
    public ChangePasswordResult changePassword(AuthModel authModel, String trackingId) {
        logger.debug("changePassword() - authModel:{}, trackingId:{}");
        authModel.setUserName(authModel.getUserName());
        User user = findUser(authModel, trackingId);
        if (user == null) {
            auditService.writeAudit(auditService.getAuditLog(trackingId, "changePassword()", authModel.toString(),
                    Calendar.getInstance()));
            return ChangePasswordResult.OTHER_FAILURE;
        } else {
            if (user.changePasswordProof == null) {
                return ChangePasswordResult.OTHER_FAILURE;
            }
            if (user.changePasswordProof.equals(authModel.getRandomString()) &&
                    authModel.getPassword().equals(authModel.getConfirmPassword())) {
                user.changePasswordProof = null;
                user.hashedPassword = authModel.getPassword();
                try {
                    userTx.begin();
                    em.merge(user);
                    userTx.commit();
                } catch (Exception ex) {
                    logger.error("changePassword() - exception:", ex);
                    return ChangePasswordResult.OTHER_FAILURE;
                }
            }
        }
        return ChangePasswordResult.SUCCESS;
    }
}

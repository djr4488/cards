package com.djr.cards.login;

import com.djr.cards.audit.AuditService;
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
public class LoginServiceImpl implements LoginService {
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

    private FindUserResult findOrCreateUser(LoginModel loginModel, String trackingId) {
        logger.debug("findOrCreateUser() - loginModel:{}, trackingId:{}", loginModel, trackingId);
        try {
            TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
            query.setParameter("emailAddress", loginModel.getEmail());
            return createFindUserResult(query.getSingleResult(), false);
        } catch (NoResultException nrEx) {
            User user = new User(loginModel);
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

    private User findUser(LoginModel loginModel, String trackingId) {
        logger.debug("findUser() - loginModel:{}, trackingId:{}", loginModel, trackingId);
        try {
            TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
            query.setParameter("emailAddress", loginModel.getUserName());
            return query.getSingleResult();
        } catch (NoResultException nrEx) {
            logger.debug("findUser() - No user found.");
            return null;
        }
    }

    @Override
    public CreateResult createUser(LoginModel loginModel, String trackingId) {
        logger.debug("createUser - loginModel:{}, trackingId:{}", loginModel, trackingId);
        FindUserResult findUserResult = findOrCreateUser(loginModel, trackingId);
        if (findUserResult == null) {
            return CreateResult.OTHER_FAILURE;
        } else if (!findUserResult.created) {
            return CreateResult.USERNAME_TAKEN;
        }
        return CreateResult.CREATED;
    }

    @Override
    public LoginResult login(LoginModel loginModel, String trackingId) {
        logger.debug("login() - loginModel:{}, trackingId:{}", loginModel, trackingId);
        User user = findUser(loginModel, trackingId);
        LoginResult loginResult = new LoginResult();
        if (user == null || !user.hashedPassword.equals(loginModel.getPassword())) {
            auditService.writeAudit(auditService.getAuditLog(trackingId, "LoginService.login()",
                    loginModel.toString(), Calendar.getInstance()));
            loginResult.result = LoginResult.ResultOptions.FAILED_USER_OR_PASS;
            loginResult.user = null;
            return loginResult;
        }
        loginResult.result = LoginResult.ResultOptions.SUCCESS;
        loginResult.user = user;
        return loginResult;
    }

    @Override
    public ForgotPasswordResult forgotPassword(LoginModel loginModel, String trackingId) {
        logger.debug("forgotPassword() - loginModel:{}, trackingId:{}", loginModel, trackingId);
        auditService.writeAudit(auditService.getAuditLog(trackingId, "forgotPassword()", loginModel.toString(),
                Calendar.getInstance()));
        loginModel.setUserName(loginModel.getEmail());
        User user = findUser(loginModel, trackingId);
        if (user == null) {
            return ForgotPasswordResult.NOT_FOUND;
        } else {
            Random r = new Random();
            Integer code = r.nextInt(50000);
            String emailBody = "Here to help, lets make it so you can change your password!\nJust use the code below " +
                    "to change your password.  If you didn't initiate this, don't change your password.\n\n" +
                    "Code -> " + code + ".";
            String subject = "Cards - Forgot Password Service";
            if (emailService.sendEmail(user.emailAddress, user.alias, subject, emailBody, session)) {
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
    public ChangePasswordResult changePassword(LoginModel loginModel, String trackingId) {
        logger.debug("changePassword() - loginModel:{}, trackingId:{}");
        loginModel.setUserName(loginModel.getEmail());
        User user = findUser(loginModel, trackingId);
        if (user == null) {
            auditService.writeAudit(auditService.getAuditLog(trackingId, "changePassword()", loginModel.toString(),
                    Calendar.getInstance()));
            return ChangePasswordResult.OTHER_FAILURE;
        } else {
            if (user.changePasswordProof == null) {
                return ChangePasswordResult.OTHER_FAILURE;
            }
            if (user.changePasswordProof.equals(loginModel.getRandomString()) &&
                    loginModel.getPassword().equals(loginModel.getConfirmPassword())) {
                user.changePasswordProof = null;
                user.hashedPassword = loginModel.getPassword();
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

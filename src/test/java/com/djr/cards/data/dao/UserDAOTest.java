package com.djr.cards.data.dao;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.data.entities.User;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * User: djr4488
 * Date: 1/31/14
 * Time: 10:21 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest  extends TestCase {
    @Mock
    private EntityManager em;
    @Mock
    private UserTransaction userTx;
    @Mock
    private Logger logger;
    @Mock
    private TypedQuery<User> query;
    @InjectMocks
    private UserDAO userDao = new UserDAOImpl();

    private AuthModel generateAuthModel(String userName, String password, String confirmPassword, String alias) {
        AuthModel authModel = new AuthModel();
        authModel.setUserName(userName);
        authModel.setPassword(password);
        authModel.setConfirmPassword(confirmPassword);
        authModel.setAlias(alias);
        return authModel;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindOrCreateUserAsFind() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        String tracking = "testFindOrCreateUserAsFind";
        User user = new User(authModel);
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        FindUserResult fur = userDao.findOrCreateUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        assertEquals(false, fur.created);
    }

    @Test
    public void testFindOrCreateUserAsCreate() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        String tracking = "testFindOrCreateUserAsCreate";
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException("No result found."));
        FindUserResult fur = userDao.findOrCreateUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        try {
            verify(userTx).begin();
            verify(em).persist(any(User.class));
            verify(userTx).commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Received exception");
        }
        assertEquals(true, fur.created);
    }

    @Test
    public void testFindOrCreateUserAsBeginFails() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        String tracking = "testFindOrCreateUserAsCreate";
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException("No result found."));
        try {
            doThrow(new SystemException("Begin tx failed")).when(userTx).begin();
        } catch (Exception ex) {
            fail("Didn't expect exception here");
        }
        FindUserResult fur = userDao.findOrCreateUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        try {
            verify(userTx).begin();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Received exception");
        }
        assertNull(fur);
    }

    @Test
    public void testFindOrCreateUserAsCommitFails() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        String tracking = "testFindOrCreateUserAsCreate";
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException("No result found."));
        try {
            doThrow(new SystemException("Begin tx failed")).when(userTx).commit();
        } catch (Exception ex) {
            fail("Didn't expect exception here");
        }
        FindUserResult fur = userDao.findOrCreateUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        try {
            verify(userTx).begin();
            verify(em).persist(any(User.class));
            verify(userTx).commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Received exception");
        }
        assertNull(fur);
    }

    @Test
    public void testFindUserAsFound() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        User user = new User(authModel);
        String tracking = "testFindUserAsFound";;
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        User foundUser = userDao.findUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindUserAsNotFound() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "test");
        String tracking = "testFindUserAsNotFound";
        when(em.createNamedQuery("findUser", User.class)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException("No user found."));
        User foundUser = userDao.findUser(authModel, tracking);
        verify(logger).debug(any(String.class), any(AuthModel.class), any(String.class));
        verify(em).createNamedQuery("findUser", User.class);
        verify(query).getSingleResult();
        assertNull(foundUser);
    }

    @Test
    public void testUpdateUserAsSuccess() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "alias");
        User user = new User(authModel);
        String tracking = "testUpdateUserAsSuccess";
        User updatedUser = userDao.updateUser(user, tracking);
        verify(em).merge(user);
        assertNotNull(updatedUser);
    }

    @Test
    public void testUpdateUserAsFailedBeginTx() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "alias");
        User user = new User(authModel);
        String tracking = "testUpdateUserAsSuccess";
        try {
            doThrow(new SystemException("Tranaction failed")).when(userTx).begin();
        } catch (Exception ex) {
            fail("didn't really expect an exception here");
        }
        User updatedUser = userDao.updateUser(user, tracking);
        verify(logger).error(any(String.class), any(Exception.class));
        assertNotNull(updatedUser);
    }

    @Test
    public void testUpdateUserAsFailedCommitTx() {
        AuthModel authModel = generateAuthModel("test", "test", "test", "alias");
        User user = new User(authModel);
        String tracking = "testUpdateUserAsSuccess";
        try {
            doThrow(new SystemException("Tranaction failed")).when(userTx).commit();
        } catch (Exception ex) {
            fail("didn't really expect an exception here");
        }
        User updatedUser = userDao.updateUser(user, tracking);
        verify(logger).error(any(String.class), any(Exception.class));
        assertNotNull(updatedUser);
    }
}

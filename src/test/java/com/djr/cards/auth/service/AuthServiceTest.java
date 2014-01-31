package com.djr.cards.auth.service;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.data.dao.UserDAO;
import com.djr.cards.data.entities.User;
import com.djr.cards.email.EmailService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;


/**
 * User: djr4488
 * Date: 1/23/14
 * Time: 8:43 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest extends TestCase {
    @Mock
    private AuditService auditSvc;
    @Mock
    private EmailService emailSvc;
    @Mock
    private UserDAO userDAO;
    @Mock
    private Logger logger;
    @InjectMocks
    private AuthService authSvc = new AuthServiceImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserSuccess() {
        assertNotNull(authSvc);
        AuthModel authModel = new AuthModel();
        authModel.setUserName("test");
        authModel.setPassword("test");
        authModel.setConfirmPassword("test");
        authModel.setAlias("test");
        String tracking = "TEST TRACKING";
        FindUserResult fur = new FindUserResult();
        fur.created = true;
        User user= new User();
        user.alias = "test";
        fur.user = user;
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(fur);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.CREATED, cr);
    }

    @Test
    public void testCreateUserExists() {
        assertNotNull(authSvc);
        AuthModel authModel = new AuthModel();
        authModel.setUserName("test");
        authModel.setPassword("test");
        authModel.setConfirmPassword("test");
        authModel.setAlias("test");
        String tracking = "TEST TRACKING";
        FindUserResult fur = new FindUserResult();
        fur.created = false;
        User user= new User();
        user.alias = "test";
        fur.user = user;
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(fur);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.USERNAME_TAKEN, cr);
    }

    @Test
    public void testCreateUserOtherFailure() {
        assertNotNull(authSvc);
        AuthModel authModel = new AuthModel();
        authModel.setUserName("test");
        authModel.setPassword("test");
        authModel.setConfirmPassword("test");
        authModel.setAlias("test");
        String tracking = "TEST TRACKING";
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(null);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.OTHER_FAILURE, cr);
    }
}

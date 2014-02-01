package com.djr.cards.auth.service;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.login.LoginResult;
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

    private AuthModel generateAuthModel(String userName, String password, String confirmPassword, String alias,
                                        String proof) {
        AuthModel authModel = new AuthModel();
        authModel.setUserName(userName);
        authModel.setPassword(password);
        authModel.setConfirmPassword(confirmPassword);
        authModel.setAlias(alias);
        authModel.setRandomString(proof);
        return authModel;
    }

    @Test
    public void testCreateUserSuccess() {
        assertNotNull(authSvc);
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        String tracking = "TEST TRACKING";
        FindUserResult fur = new FindUserResult();
        fur.created = true;
        fur.user = user;
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(fur);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.CREATED, cr);
    }

    @Test
    public void testCreateUserExists() {
        assertNotNull(authSvc);
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        String tracking = "TEST TRACKING";
        FindUserResult fur = new FindUserResult();
        fur.created = false;
        user.alias = "test";
        fur.user = user;
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(fur);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.USERNAME_TAKEN, cr);
    }

    @Test
    public void testCreateUserOtherFailure() {
        assertNotNull(authSvc);
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        String tracking = "TEST TRACKING";
        when(userDAO.findOrCreateUser(authModel, tracking)).thenReturn(null);
        AuthService.CreateResult cr = authSvc.createUser(authModel, tracking);
        assertEquals(AuthService.CreateResult.OTHER_FAILURE, cr);
    }

    @Test
    public void testLoginSuccess() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        LoginResult lr = authSvc.login(authModel, tracking);
        assertEquals(LoginResult.ResultOptions.SUCCESS, lr.result);
    }

    @Test
    public void testFailedNoUser() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(null);
        LoginResult lr = authSvc.login(authModel, tracking);
        assertEquals(LoginResult.ResultOptions.FAILED_USER_OR_PASS, lr.result);
    }

    @Test
    public void testFailedWrongPassword() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        user.hashedPassword = "testWrong";
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        LoginResult lr = authSvc.login(authModel, tracking);
        assertEquals(LoginResult.ResultOptions.FAILED_USER_OR_PASS, lr.result);
    }

    @Test
    public void testForgotPasswordNoUser() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(null);
        AuthService.ForgotPasswordResult result = authSvc.forgotPassword(authModel, tracking);
        assertEquals(AuthService.ForgotPasswordResult.NOT_FOUND, result);
    }

    @Test
    public void testForgotPasswordEmailFails() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        when(emailSvc.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(false);
        AuthService.ForgotPasswordResult result = authSvc.forgotPassword(authModel, tracking);
        verify(emailSvc).sendEmail(any(String.class), any(String.class), any(String.class), any(String.class));
        assertEquals(AuthService.ForgotPasswordResult.OTHER_FAILURE, result);
    }

    @Test
    public void testForgotPasswordEmailSends() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        when(emailSvc.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
        AuthService.ForgotPasswordResult result = authSvc.forgotPassword(authModel, tracking);
        verify(emailSvc).sendEmail(any(String.class), any(String.class), any(String.class), any(String.class));
        verify(userDAO).updateUser(any(User.class), any(String.class));
        assertEquals(AuthService.ForgotPasswordResult.SUCCESS, result);
    }

    @Test
    public void testChangePasswordNoUser() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(null);
        AuthService.ChangePasswordResult result = authSvc.changePassword(authModel, tracking);
        assertEquals(AuthService.ChangePasswordResult.OTHER_FAILURE, result);
    }

    @Test
    public void testChangePasswordWrongProof() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        user.changePasswordProof = "wrong";
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        AuthService.ChangePasswordResult result = authSvc.changePassword(authModel, tracking);
        assertEquals(AuthService.ChangePasswordResult.OTHER_FAILURE, result);
    }

    @Test
    public void testChangePasswordSuccess() {
        AuthModel authModel = generateAuthModel("test@test.com", "test", "test", "alias", "proof");
        User user = new User(authModel);
        user.changePasswordProof = "proof";
        String tracking = "TEST TRACKING";
        when(userDAO.findUser(authModel, tracking)).thenReturn(user);
        when(userDAO.updateUser(any(User.class), any(String.class))).thenReturn(user);
        AuthService.ChangePasswordResult result = authSvc.changePassword(authModel, tracking);
        verify(userDAO).updateUser(any(User.class), any(String.class));
        assertEquals(AuthService.ChangePasswordResult.SUCCESS, result);
    }
}

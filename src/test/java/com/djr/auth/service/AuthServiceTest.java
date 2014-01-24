package com.djr.auth.service;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.data.dao.UserDAO;
import com.djr.cards.data.entities.User;
import junit.framework.TestCase;
import org.jglue.cdiunit.CdiRunner;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/23/14
 * Time: 8:43 AM
 */
@RunWith(CdiRunner.class)
public class AuthServiceTest extends TestCase {
    @Inject
    private AuthService authService;
    private UserDAO userDao;
    private Mockery context;


    @Alternative
    public UserDAO userDaoProducer(){
        context = new Mockery();
        userDao = context.mock(UserDAO.class);
        return userDao;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Test
    public void testCreateUser() {
        final AuthModel authModel = new AuthModel();
        final String trackingId = "test create user tracking id";
        final FindUserResult findUserResult = new FindUserResult();
        findUserResult.user = new User();
        findUserResult.created = true;
        //setup a valid user
        authModel.setUserName("test@test.com");
        authModel.setPassword("test");
        authModel.setAlias("test");
        context.checking(
            new Expectations() {{
                oneOf(userDao).findOrCreateUser(authModel, trackingId); will(returnValue(findUserResult));
            }}
        );
        AuthService.CreateResult result = authService.createUser(authModel, trackingId);
        assertEquals(AuthService.CreateResult.CREATED, result);
    }
}

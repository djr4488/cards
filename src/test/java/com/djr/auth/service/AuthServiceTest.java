package com.djr.auth.service;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.AuthService;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.data.dao.UserDAO;
import junit.framework.TestCase;
import org.easymock.EasyMockRunner;
import static org.easymock.EasyMock.*;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/23/14
 * Time: 8:43 AM
 */
//@RunWith(EasyMockRunner.class)
public class AuthServiceTest extends TestCase {
    /*@TestSubject
    private com.djr.cards.auth.service.AuthServiceImpl authService;
    @Mock
    private UserDAO userDao;

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
        userDao.findOrCreateUser(authModel, trackingId);
        replay(userDao);
        AuthService.CreateResult result = authService.createUser(authModel, trackingId);
        assertEquals(AuthService.CreateResult.CREATED, result);
    }  */
}

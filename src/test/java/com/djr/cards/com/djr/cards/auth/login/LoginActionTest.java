package com.djr.cards.com.djr.cards.auth.login;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.login.LoginAction;
import com.djr.cards.auth.login.LoginResult;
import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.util.HashingUtil;
import com.djr.cards.data.entities.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.apache.struts2.StrutsTestCase;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
import org.slf4j.Logger;

/**
 * @author dannyrucker
 *         Date: 2/3/14
 *         Time: 1:26 PM
 */
@RunWith (MockitoJUnitRunner.class)
public class LoginActionTest extends TestCase {
	@Mock
	private Logger logger;
	@Mock
	private AuditService auditService;
	@Mock
	private AuthService authService;
	@Mock
	private HashingUtil hashingUtil;
	@Mock
	private AuthModel authModel;

	@InjectMocks
	private LoginAction loginAction = new LoginAction();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoginSuccess() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		LoginResult result = new LoginResult();
		result.user = new User();
		result.result = LoginResult.ResultOptions.SUCCESS;
		when(authService.login(any(AuthModel.class), any(String.class))).thenReturn(result);
		when(request.getSession(false)).thenReturn(session);
		loginAction.setServletRequest(request);
		String actionResult = loginAction.loginExecute();
		verify(session).setAttribute("user", result.user);
		assertEquals("success", actionResult);
	}
}

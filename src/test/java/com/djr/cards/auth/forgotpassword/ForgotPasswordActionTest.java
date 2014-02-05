package com.djr.cards.auth.forgotpassword;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.forgotpassword.ForgotPasswordAction;
import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.util.HashingUtil;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author dannyrucker
 *         Date: 2/3/14
 *         Time: 4:45 PM
 */
@RunWith (MockitoJUnitRunner.class)
public class ForgotPasswordActionTest extends TestCase {
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
	private ForgotPasswordAction forgotPasswordAction = new ForgotPasswordAction();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testForgotPasswordSuccess() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		forgotPasswordAction.setServletRequest(request);
		AuthService.ForgotPasswordResult success = AuthService.ForgotPasswordResult.SUCCESS;
		when(request.getSession(false)).thenReturn(session);
		when(session.getAttribute("tracking")).thenReturn("testForgotPasswordSuccess");
		when(authService.forgotPassword(any(AuthModel.class), any(String.class))).thenReturn(success);
		String actionResult = forgotPasswordAction.forgotPasswordExecute();
		assertEquals("success", actionResult);
	}
}

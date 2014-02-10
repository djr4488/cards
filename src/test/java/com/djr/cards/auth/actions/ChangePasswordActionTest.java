package com.djr.cards.auth.actions;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.actions.ChangePasswordAction;
import com.djr.cards.auth.service.AuthService;
import com.djr.cards.auth.util.HashingUtil;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author dannyrucker
 *         Date: 2/3/14
 *         Time: 5:07 PM
 */
@RunWith (MockitoJUnitRunner.class)
public class ChangePasswordActionTest extends TestCase {
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
	private ChangePasswordAction changePasswordAction = new ChangePasswordAction();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testChangePasswordSuccess() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		changePasswordAction.setServletRequest(request);
		AuthService.ChangePasswordResult result = AuthService.ChangePasswordResult.SUCCESS;
		when(request.getSession(false)).thenReturn(session);
		when(session.getAttribute("tracking")).thenReturn("testCreateAccountSuccess");
		when(authService.changePassword(any(AuthModel.class), any(String.class))).thenReturn(result);
		String actionResult = changePasswordAction.changePasswordExecute();
		assertEquals("success", actionResult);
	}
}

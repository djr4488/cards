package com.djr.cards.auth.create;

import com.djr.cards.audit.AuditService;
import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.create.CreateAccountAction;
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
 *         Time: 5:02 PM
 */
@RunWith (MockitoJUnitRunner.class)
public class CreateAccountActionTest extends TestCase {
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
	private CreateAccountAction createAccountAction = new CreateAccountAction();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateAccountSuccess() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		createAccountAction.setServletRequest(request);
		AuthService.CreateResult result = AuthService.CreateResult.CREATED;
		when(request.getSession(false)).thenReturn(session);
		when(session.getAttribute("tracking")).thenReturn("testCreateAccountSuccess");
		when(authService.createUser(any(AuthModel.class), any(String.class))).thenReturn(result);
		String actionResult = createAccountAction.createAccountExecute();
		assertEquals("success", actionResult);
	}
}

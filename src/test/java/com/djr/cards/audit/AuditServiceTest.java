package com.djr.cards.audit;

import com.djr.cards.cdiproducers.LoggerProducer;
import com.djr.cards.data.entities.AuditLog;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ProducesAlternative;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.Calendar;
import static org.mockito.Mockito.*;

/**
 * @author dannyrucker
 *         Date: 2/3/14
 *         Time: 10:16 AM
 */
@RunWith (MockitoJUnitRunner.class)
public class AuditServiceTest extends TestCase {
	@Mock
	private EntityManager em;
	@Mock
	private Logger logger;

	@InjectMocks
	private AuditService auditService = new AuditServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAudit() {
		AuditLog auditLog = auditService.getAuditLog("AuditServiceTest", "testGetAudit()", "Ensuring not null",
				Calendar.getInstance());
		verify(logger).debug(any(String.class),any(String.class), any(String.class), any(String.class),
				any(Calendar.class));
		assertNotNull(auditLog);
		assertEquals("AuditServiceTest", auditLog.who);
		assertEquals("testGetAudit()", auditLog.what);
		assertEquals("Ensuring not null", auditLog.info);
		assertNotNull(auditLog.time);
	}

	@Test
	public void testWriteAuditSuccess() {
		AuditLog auditLog = auditService.getAuditLog("AuditServiceTest", "testGetAudit()", "Ensuring not null",
				Calendar.getInstance());
		auditService.writeAudit(auditLog);
		verify(em).persist(auditLog);
	}
}

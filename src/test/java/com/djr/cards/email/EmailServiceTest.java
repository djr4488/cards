package com.djr.cards.email;

import com.djr.cards.cdiproducers.LoggerProducer;
import com.djr.cards.cdiproducers.PropertyProducer;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import javax.inject.Inject;

import static org.mockito.Mockito.*;

/**
 * User: djr4488
 * Date: 2/1/14
 * Time: 11:16 AM
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({PropertyProducer.class, LoggerProducer.class, EmailServiceImpl.class})
public class EmailServiceTest extends TestCase {
	@Inject
	private EmailService emailSvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testSendEmailSuccess() {
		assertNotNull(emailSvc);
		assertTrue(emailSvc.sendEmail("djr4488@gmail.com", "Test", "Test", "Testing email"));
	}
}

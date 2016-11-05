package com.djr.cards.email;

import com.djr.cards.cdiproducers.LoggerProducer;
import com.djr.cards.cdiproducers.PropertyProducer;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetupTest;
import junit.framework.TestCase;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import javax.inject.Inject;

import static org.mockito.Mockito.*;

/**
 * User: djr4488
 * Date: 2/1/14
 * Time: 11:16 AM
 *
 * TODO: fix test case so can not have a need for real credentials to be used
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({LoggerProducer.class, EmailServiceImpl.class})
@ActivatedAlternatives({EmailServiceConfigProducer.class})
public class EmailServiceTest extends TestCase {
	@Inject
	private EmailService emailSvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Ignore
	public void testSendEmailSuccess() {
		assertNotNull(emailSvc);
		assertTrue(emailSvc.sendEmail("djr4488@gmail.com", "Test", "Test", "Testing email"));
	}

	@Test
	public void testPlaceHolder() {
		assertTrue(true);
	}
}

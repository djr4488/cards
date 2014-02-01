package com.djr.cards.email;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;

/**
 * User: djr4488
 * Date: 2/1/14
 * Time: 11:16 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest extends TestCase {
    @Mock
    private Logger logger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}

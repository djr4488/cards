package com.djr.cards.cdiproducers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/6/14
 * Time: 9:04 AM
 */
@ApplicationScoped
@Default
public class LoggerProducer {
    private static final Logger log = LoggerFactory.getLogger(LoggerProducer.class);

    @Produces
    public Logger getLogger(InjectionPoint ip) {
        Class<?> injectingClass = ip.getMember().getDeclaringClass();
        log.debug("getLogger() injectingClass:{}", injectingClass.getName());
        return LoggerFactory.getLogger(injectingClass);
    }
}

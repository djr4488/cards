package com.djr.cards.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


/**
 * Created by djr4488 on 11/5/16.
 */
@Alternative
public class EmailServiceConfigProducer {
    @Produces
    public String getStringProperty(InjectionPoint injectionPoint) {
        String member = injectionPoint.getMember().getName();
        String value = "";
        switch (member) {
            case "host":
                value = "host";
                break;
            case "port":
                value = "443";
                break;
            case "username":
                value = "username";
                break;
            case "password":
                value = "password";
                break;
            default:
                value = "";
        }
        return value;
    }

    @Produces
    public boolean getBooleanProperty(InjectionPoint injectionPoint) {
        String member = injectionPoint.getMember().getName();
        Boolean value = false;
        switch (member) {
            case "authRequired":
                value = true;
                break;
            case "enableTls":
                value = false;
                break;
            default:
                value = false;
        }
        return value;
    }

    @Produces
    public Integer getIntegerProperty(InjectionPoint injectionPoint) {
        String member = injectionPoint.getMember().getName();
        Integer value;
        switch (member) {
            case "timeout":
                value = 500;
                break;
            default:
                value = 0;
        }
        return value;
    }

    @Produces
    public Logger getLotter(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getClass().getName());
    }
}

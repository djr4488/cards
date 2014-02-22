package com.djr.cards.auth.restapi.login;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 8:24 AM
 */
@ApplicationPath("/cards")
public class LoginApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(JSONEndpoint.class));
    }
}

package com.djr.cards;

import com.djr.cards.auth.restapi.createaccount.CreateAccountEndpoint;
import com.djr.cards.auth.restapi.forgotpassword.ForgotPasswordEndpoint;
import com.djr.cards.auth.restapi.login.LoginEndpoint;
import com.djr.cards.auth.restapi.resetpassword.ResetPasswordEndpoint;
import com.djr.cards.games.selector.restapi.GameSelectionEndpoint;

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
@ApplicationPath("/cardsapi")
public class CardsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(LoginEndpoint.class, CreateAccountEndpoint.class,
                ForgotPasswordEndpoint.class, ResetPasswordEndpoint.class, GameSelectionEndpoint.class));
    }
}

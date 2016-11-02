package com.djr.cards;

import com.djr.cards.auth.restapi.createaccount.CreateAccountController;
import com.djr.cards.auth.restapi.forgotpassword.ForgotPasswordController;
import com.djr.cards.auth.restapi.login.LoginController;
import com.djr.cards.auth.restapi.resetpassword.ResetPasswordController;
import com.djr.cards.games.golf.restapi.GolfStatsController;
import com.djr.cards.games.restapi.GameController;
import com.djr.cards.games.selector.restapi.GameSelectionController;
import com.djr4488.metrics.rest.MetricsApi;

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
		return new HashSet<Class<?>>(Arrays.asList(MetricsApi.class, LoginController.class, CreateAccountController.class,
				ForgotPasswordController.class, ResetPasswordController.class, GameSelectionController.class,
				GolfStatsController.class, GameController.class));
	}
}

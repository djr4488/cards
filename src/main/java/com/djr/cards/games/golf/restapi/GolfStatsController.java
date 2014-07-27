package com.djr.cards.games.golf.restapi;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.BaseGameController;
import com.djr.cards.games.stats.GameStatsService;
import com.djr.cards.games.stats.model.GameStats;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/20/2014
 * Time: 7:12 PM
 */
@Path("golfStats")
@ApplicationScoped
public class GolfStatsController extends BaseGameController {
	@Inject
	private Logger log;
	@Inject
	private GameStatsService gameStatsSvc;

	@GET
	@Path("get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GameStats getPlayStats(@Context HttpServletRequest request) {
		String tracking = (String) getSession(request).getAttribute("tracking");
		User user = (User) getSession(request).getAttribute("user");
		log.info("getPlayStats() - tracking:{}", tracking);
		GameStats response = gameStatsSvc.loadGameStats(tracking, user, "Golf");
		log.debug("getPlayStats() - response:{}", response);
		return response;
	}
}

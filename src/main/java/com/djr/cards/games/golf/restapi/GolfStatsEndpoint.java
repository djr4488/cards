package com.djr.cards.games.golf.restapi;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/20/2014
 * Time: 7:12 PM
 */
@Path("golfStats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class GolfStatsEndpoint extends GolfStatsController {
}

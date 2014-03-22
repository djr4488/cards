package com.djr.cards.games.restapi;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/22/2014
 * Time: 12:23 AM
 */
@Path("gamesvc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class GameEndpoint extends GameController {
}

package com.djr.cards.games.selector.restapi;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 11:01 PM
 */
@Path("gameSelection")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class GameSelectionEndpoint extends GameSelectionController {
}

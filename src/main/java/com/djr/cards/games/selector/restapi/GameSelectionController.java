package com.djr.cards.games.selector.restapi;

import com.djr.cards.games.BaseGameController;
import com.djr.cards.games.selector.SelectorService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 11:01 PM
 */
public abstract class GameSelectionController extends BaseGameController {
    @Inject
    private Logger logger;
    @Inject
    private SelectorService selectorSvc;

    @GET
    @Path("get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameSelectionResponse getGameOptions(@Context HttpServletRequest request) {
        String tracking = (String)getSession(request).getAttribute("tracking");
        logger.info("getGameOptions() - tracking:{}", tracking);
        GameSelectionResponse response = new GameSelectionResponse();
        response.gameOpts = selectorSvc.getGameList(tracking);
        if (response.gameOpts == null || response.gameOpts.size() == 0) {
            response.gameOpts = null;
            response.errorMsg = "Couldn't load the game options.  I've logged the error.";
            response.errorBold = "Son of a monkey's uncle!  ";
        }
        return response;
    }

    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameSelectionResponse submit(SelectedGameRequest selectedGameRequest, @Context HttpServletRequest request) {
        return null;
    }
}

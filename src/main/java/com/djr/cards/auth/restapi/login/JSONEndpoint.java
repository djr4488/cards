package com.djr.cards.auth.restapi.login;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author dannyrucker
 *         Date: 2/22/14
 *         Time: 8:21 AM
 */
@Path("json/auth/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class JSONEndpoint extends RestLoginFormController {

}

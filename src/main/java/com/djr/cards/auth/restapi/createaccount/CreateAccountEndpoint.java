package com.djr.cards.auth.restapi.createaccount;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author dannyrucker
 *         Date: 3/12/14
 *         Time: 5:04 PM
 */
@Path("createAccount/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CreateAccountEndpoint extends CreateAccountController {
}

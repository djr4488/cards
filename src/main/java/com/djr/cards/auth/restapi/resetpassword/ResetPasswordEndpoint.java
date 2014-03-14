package com.djr.cards.auth.restapi.resetpassword;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author dannyrucker
 *         Date: 3/14/14
 *         Time: 2:09 PM
 */
@Path("resetpassword/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ResetPasswordEndpoint extends ResetPasswordController {
}

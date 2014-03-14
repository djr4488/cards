package com.djr.cards.auth.restapi.forgotpassword;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: djr4488
 * Date: 3/13/14
 * Time: 9:46 PM
 */
@Path("forgotpassword/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ForgotPasswordEndpoint extends ForgotPasswordController {
}

package com.terziev.konstantin.accountapi.api;

import com.terziev.konstantin.accountapi.model.Account;
import com.terziev.konstantin.accountapi.service.AccountService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/accounts")
@ApplicationScoped
public class AccountApi {

    @Inject
    private AccountService accountService;

    @GET
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccount(@PathParam("accountId") final String accountId) {
        return accountService.findAccount(accountId)
            .map(account -> Response.ok().entity(account).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(final Account account) {
        final Account newAccount = accountService.addAccount(account);

        return Response.status(Response.Status.CREATED).entity(newAccount).build();
    }

}

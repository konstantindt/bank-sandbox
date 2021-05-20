package com.terziev.konstantin.statementapi.api;

import com.terziev.konstantin.statementapi.model.Statement;
import com.terziev.konstantin.statementapi.service.StatementService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/statements")
@ApplicationScoped
public class StatementApi {

    @Inject
    private StatementService statementService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatement(@QueryParam("accountId") final String accountId) {
        return Response.ok().entity(statementService.getStatement(accountId)).build();
    }

}

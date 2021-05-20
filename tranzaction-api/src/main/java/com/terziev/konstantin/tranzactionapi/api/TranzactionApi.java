package com.terziev.konstantin.tranzactionapi.api;

import com.terziev.konstantin.tranzactionapi.model.Tranzaction;
import com.terziev.konstantin.tranzactionapi.service.TranzactionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/tranzactions")
@ApplicationScoped
public class TranzactionApi {

    @Inject
    private TranzactionService tranzactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTranzactionByPartyId(@QueryParam("partyId") final String partyId) {
        return Response.ok()
            .entity(tranzactionService.findTranzactionsByPartyId(partyId))
            .build();
    }

    @GET
    @Path("{tranzactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTranzaction(@PathParam("tranzactionId") final String tranzactionId) {
        return tranzactionService.findTranzaction(tranzactionId)
            .map(tranzaction -> Response.ok().entity(tranzaction).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTranzaction(final Tranzaction tranzaction) {
        final Tranzaction newTranzaction = tranzactionService.addTranzaction(tranzaction);

        return Response.status(Response.Status.CREATED).entity(newTranzaction).build();
    }

}

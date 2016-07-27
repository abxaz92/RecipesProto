package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.Sourcefunding;
import ru.macrobit.recept.service.SourcefundingService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/sourcefunding")
@Produces(MediaType.APPLICATION_JSON)
public class SourcefundingController {
    private static final Logger log = LoggerFactory.getLogger(SourcefundingController.class);

    @Inject
    private SourcefundingService sourcefundingService;

    @GET
    @Path("/{id}")
    public Sourcefunding getById(@PathParam("id") Long id) {
        return sourcefundingService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return sourcefundingService.findAll(jsonQuery == null ? null : new JSONObject(jsonQuery), skip, limit, count, sortProperties, sortDirection);
    }

    @POST
    public Sourcefunding post(Sourcefunding sourcefunding) throws Exception {
        sourcefundingService.insert(sourcefunding);
        return sourcefunding;
    }

    @PUT
    @Path("/{id}")
    public Sourcefunding put(JsonNode sourcefunding, @PathParam("id") Long id) throws Exception {
        return sourcefundingService.update(id, sourcefunding);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        sourcefundingService.deleteById(id);
    }
}

package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.drug.TorgName;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.TorgNameService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by [david] on 12.09.16.
 */
@Path("/drugname")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class TorgNameController {
    private static final Logger log = LoggerFactory.getLogger(TorgNameController.class);

    @Inject
    private TorgNameService torgNameService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public TorgName getById(@PathParam("id") String id) {
        return torgNameService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return torgNameService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public TorgName post(TorgName torgName) throws Exception {
        torgNameService.insert(torgName);
        return torgName;
    }

    @PUT
    @Path("/{id}")
    public TorgName put(JsonNode torgName, @PathParam("id") String id) throws Exception {
        return torgNameService.update(id, torgName, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        torgNameService.deleteById(id, ctx.getCurrentUser());
    }
}

package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.drug.MnnName;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.MnnNameService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by [david] on 12.09.16.
 */
@Path("/drugnameint")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class MnnNameController {
    private static final Logger log = LoggerFactory.getLogger(MnnNameController.class);

    @Inject
    private MnnNameService mnnNameService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public MnnName getById(@PathParam("id") String id) {
        return mnnNameService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return mnnNameService.find(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public MnnName post(MnnName mnnName) throws Exception {
        mnnNameService.insert(mnnName);
        return mnnName;
    }

    @PUT
    @Path("/{id}")
    public MnnName put(JsonNode mnnName, @PathParam("id") String id) throws Exception {
        return mnnNameService.update(id, mnnName, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        mnnNameService.deleteById(id, ctx.getCurrentUser());
    }
}

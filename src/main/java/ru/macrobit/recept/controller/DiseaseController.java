package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Disease;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.DiseaseService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by [david] on 12.09.16.
 */
@Path("/disease")
@Produces(MediaType.APPLICATION_JSON)
public class DiseaseController {
    private static final Logger log = LoggerFactory.getLogger(DiseaseController.class);

    @Inject
    private DiseaseService diseaseService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public Disease getById(@PathParam("id") String id) {
        return diseaseService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return diseaseService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public Disease post(Disease disease) throws Exception {
        diseaseService.insert(disease);
        return disease;
    }

    @PUT
    @Path("/{id}")
    public Disease put(JsonNode disease, @PathParam("id") Long id) throws Exception {
        return diseaseService.update(id, disease, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        diseaseService.deleteById(id, ctx.getCurrentUser());
    }
}

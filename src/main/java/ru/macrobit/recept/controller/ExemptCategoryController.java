package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.ExemptCategory;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.ExemptCategoryService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by [david] on 12.09.16.
 */
@Path("/benefit")
@Produces(MediaType.APPLICATION_JSON)
public class ExemptCategoryController {
    private static final Logger log = LoggerFactory.getLogger(ExemptCategoryController.class);

    @Inject
    private ExemptCategoryService exemptCategoryService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public ExemptCategory getById(@PathParam("id") String id) {
        return exemptCategoryService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return exemptCategoryService.find(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public ExemptCategory post(ExemptCategory exemptCategory) throws Exception {
        exemptCategoryService.insert(exemptCategory);
        return exemptCategory;
    }

    @POST
    @Path("/multi")
    public List<ExemptCategory> post(List<ExemptCategory> categories) throws Exception {
        exemptCategoryService.insert(categories);
        return categories;
    }

    @PUT
    @Path("/{id}")
    public ExemptCategory put(JsonNode exemptCategory, @PathParam("id") String id) throws Exception {
        return exemptCategoryService.update(id, exemptCategory, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        exemptCategoryService.deleteById(id, ctx.getCurrentUser());
    }
}

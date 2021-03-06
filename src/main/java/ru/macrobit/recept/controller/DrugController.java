package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.drug.Drug;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.DrugService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by [david] on 12.07.16.
 */
@Path("/drug")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class DrugController {
    private static final Logger log = LoggerFactory.getLogger(DrugController.class);

    @Inject
    private DrugService drugService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public Drug getById(@PathParam("id") String id) {
        return drugService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return drugService.find(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public Drug post(Drug drug) throws Exception {
        drugService.insert(drug);
        return drug;
    }

    @PUT
    @Path("/{id}")
    public Drug put(JsonNode drug, @PathParam("id") String id) throws Exception {
        return drugService.update(id, drug, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        drugService.deleteById(id, ctx.getCurrentUser());
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        return drugService.uploadDBF(input);
    }
}

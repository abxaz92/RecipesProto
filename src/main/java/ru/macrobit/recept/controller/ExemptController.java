package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.service.ExemptService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/exempt")
@Produces(MediaType.APPLICATION_JSON)
public class ExemptController {
    private static final Logger log = LoggerFactory.getLogger(ExemptController.class);

    @Inject
    private ExemptService exemptService;

    @GET
    @Path("/{id}")
    public Exempt getById(@PathParam("id") String id) {
        return exemptService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return exemptService.findAll(jsonQuery == null ? null : new JSONObject(jsonQuery), skip, limit, count, sortProperties, sortDirection);
    }

    @POST
    public Exempt post(Exempt exempt) throws Exception {
        exemptService.insert(exempt);
        return exempt;
    }

    @PUT
    @Path("/{id}")
    public void put(JsonNode exempt, @PathParam("id") String id) throws Exception {
        exemptService.update(id, exempt);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        exemptService.deleteById(id);
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        return exemptService.uploadDBF(input);
    }
}

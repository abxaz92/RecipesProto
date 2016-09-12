package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptId;
import ru.macrobit.recept.pojo.LightExempt;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.ExemptService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/exempt")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class ExemptController {
    private static final Logger log = LoggerFactory.getLogger(ExemptController.class);

    @Inject
    private ExemptService exemptService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{doc}/{type}")
    public Exempt getById(@PathParam("doc") String id, @PathParam("type") int exemptType) {
        return exemptService.findById(new ExemptId(id, ExemptType.values()[exemptType]), ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return exemptService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, null, LightExempt.class);
    }

    @POST
    public Exempt post(Exempt exempt) throws Exception {
        exemptService.insert(exempt);
        return exempt;
    }

    @PUT
    @Path("/{id}")
    public Exempt put(JsonNode exempt, @PathParam("id") Long id) throws Exception {
        return exemptService.update(id, exempt, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        exemptService.deleteById(id, ctx.getCurrentUser());
    }

    @POST
    @Path("/file/mintrud")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        return exemptService.uploadMintrudDBF(input);
    }

    @POST
    @Path("/file/mz")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadMzDBF(MultipartFormDataInput input) throws IOException {
        return exemptService.uploadMZDBF(input);
    }

    @POST
    @Path("/file/federal")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadFederalDBF(MultipartFormDataInput input) throws IOException {
        return exemptService.uploadFederalDBF(input);
    }
}

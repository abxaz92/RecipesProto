package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptId;
import ru.macrobit.recept.pojo.IllegalExempt;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.IllegalExemptService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by [david] on 12.09.16.
 */
@Path("/illegalexempt")
@Produces(MediaType.APPLICATION_JSON)
public class IllegalExemptController {
    private static final Logger log = LoggerFactory.getLogger(IllegalExemptController.class);

    @Inject
    private IllegalExemptService illegalExemptService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public IllegalExempt getById(@PathParam("id") String id) {
        log.info(id);
        ExemptId exemptId = Exempt.parseExemptId(id);
        if (exemptId == null) return null;
        log.info("{} {}", exemptId.getId(), exemptId.getType());
        return illegalExemptService.findById(exemptId, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return illegalExemptService.find(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class),
                skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public IllegalExempt post(IllegalExempt illegalExempt) throws Exception {
        illegalExemptService.insert(illegalExempt);
        return illegalExempt;
    }

    @PUT
    @Path("/{id}")
    public IllegalExempt put(JsonNode illegalExempt, @PathParam("id") String id) throws Exception {
        ExemptId exemptId = Exempt.parseExemptId(id);
        if (exemptId == null) return null;
        return illegalExemptService.update(exemptId, illegalExempt, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        ExemptId exemptId = Exempt.parseExemptId(id);
        if (exemptId == null) return;
        illegalExemptService.deleteById(exemptId, ctx.getCurrentUser());
    }
}

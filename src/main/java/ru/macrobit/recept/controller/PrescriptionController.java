package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Prescription;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.PrescriptionService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 08.07.16.
 */
@ApplicationScoped
@Path("/prescription")
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@RolesAllowed({"ADMIN", "MIAC"})
public class PrescriptionController {
    @Inject
    private PrescriptionService prescriptionService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public Object getById(@PathParam("id") String id) {
        return prescriptionService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return prescriptionService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public Prescription post(Prescription prescription) throws Exception {
        prescriptionService.insert(prescription);
        return prescription;
    }
}

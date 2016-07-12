package ru.macrobit.recept.controller;

import org.json.JSONObject;
import ru.macrobit.recept.pojo.Prescription;
import ru.macrobit.recept.service.PrescriptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 08.07.16.
 */
@ApplicationScoped
@Path("/prescription")
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
public class PrescriptionController {
    @Inject
    private PrescriptionService prescriptionService;

    @PersistenceContext
    protected EntityManager em;

    @GET
    @Path("/{id}")
    public Object getById(@PathParam("id") String id) {
        return prescriptionService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return prescriptionService.findAll(jsonQuery == null ? null : new JSONObject(jsonQuery), skip, limit, sortProperties, sortDirection);
    }

    @POST
    public Prescription post(Prescription prescription) throws Exception {
        prescriptionService.insert(prescription);
        return prescription;
    }
}

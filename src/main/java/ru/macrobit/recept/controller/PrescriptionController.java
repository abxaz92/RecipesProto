package ru.macrobit.recept.controller;

import ru.macrobit.recept.pojo.Prescription;
import ru.macrobit.recept.service.PrescriptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @POST
    public Prescription post(Prescription prescription) throws Exception {
        prescriptionService.insert(prescription);
        return prescription;
    }
}

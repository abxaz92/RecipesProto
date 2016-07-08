package ru.macrobit.recept.controller;

import ru.macrobit.recept.pojo.Prescription;
import ru.macrobit.recept.service.PrescriptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by david on 08.07.16.
 */
@ApplicationScoped
@Path("/prescription")
public class PrescriptionController {

    @Inject
    private PrescriptionService prescriptionService;

    @PersistenceContext
    protected EntityManager em;

    @GET
    @Path("/{id}")
    public Object getById(@PathParam("id") Long id) {
        return em.find(Prescription.class, id);
    }
}

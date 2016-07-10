package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.Doctor;
import ru.macrobit.recept.service.DoctorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/10/16.
 */
@Path("/doctor")
@Produces(MediaType.APPLICATION_JSON)
public class DoctorController {
    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Inject
    private DoctorService doctorService;

    @GET
    @Path("/{id}")
    public Doctor getById(@PathParam("id") Long id) {
        return doctorService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {

        JSONObject obj = null;
        if (jsonQuery != null)
            obj = new JSONObject(jsonQuery);
        return doctorService.findAll(obj, skip, limit, sortProperties, sortDirection);
    }

    @POST
    public Doctor post(Doctor doctor) throws Exception {
        doctorService.insert(doctor);
        return doctor;
    }

    @PUT
    @Path("/{id}")
    public void put(JsonNode doctor, @PathParam("id") Long id) throws Exception {
        doctorService.update(id, doctor);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        doctorService.deleteById(id);
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        return doctorService.uploadDBF(input);
    }
}

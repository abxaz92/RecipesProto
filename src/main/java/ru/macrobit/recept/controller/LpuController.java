package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.hibernate.criterion.Order;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.Lpu;
import ru.macrobit.recept.service.LpuService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by david on 7/8/16
 */
@Path("/lpu")
@Produces(MediaType.APPLICATION_JSON)
public class LpuController {
    private static final Logger log = LoggerFactory.getLogger(LpuController.class);

    @Inject
    private LpuService lpuService;

    @GET
    @Path("/{id}")
    public Lpu getById(@PathParam("id") Long id) {
        return lpuService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {

        return lpuService.findAll(null, skip, limit, sortProperties, sortDirection);
    }

    @POST
    public Lpu post(Lpu lpu) throws Exception {
        lpuService.insert(lpu);
        return lpu;
    }

    @PUT
    @Path("/{id}")
    public void put(JsonNode lpu, @PathParam("id") Long id) throws Exception {
        lpuService.update(id, lpu);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        lpuService.deleteById(id);
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        return lpuService.uploadDBF(input);
    }
}

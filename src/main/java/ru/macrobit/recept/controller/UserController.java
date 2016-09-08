package ru.macrobit.recept.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.JsonViews;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.User;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    @JsonView(JsonViews.Public.class)
    public User getById(@PathParam("id") String id) {
        return userService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    @JsonView(JsonViews.Public.class)
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return userService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    @JsonView(JsonViews.Public.class)
    public User post(User user) throws Exception {
        userService.insert(user);
        return user;
    }

    @PUT
    @Path("/{id}")
    @JsonView(JsonViews.Public.class)
    public User put(JsonNode user, @PathParam("id") String id) throws Exception {
        return userService.update(id, user, ctx.getCurrentUser());
    }

    @PUT
    @Path("/password/{id}/{pass}")
    public void updatePassword(@PathParam("id") String username, @PathParam("pass") String newPass) {
        userService.updatePassword(username, newPass, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        userService.deleteById(id, ctx.getCurrentUser());
    }
}

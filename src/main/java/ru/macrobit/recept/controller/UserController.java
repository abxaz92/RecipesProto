package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.User;
import ru.macrobit.recept.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN"})
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") String id) {
        return userService.findById(id);
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return userService.findAll(jsonQuery == null ? null : new JSONObject(jsonQuery), skip, limit, count, sortProperties, sortDirection);
    }

    @POST
    public User post(User user) throws Exception {
        userService.insert(user);
        return user;
    }

    @PUT
    @Path("/{id}")
    public User put(JsonNode user, @PathParam("id") String id) throws Exception {
        return userService.update(id, user);
    }

    @PUT
    @Path("/{id}/{pass}")
    public void updatePassword(@PathParam("id") String username, @PathParam("pass") String newPass) {
        userService.updatePassword(username, newPass);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        userService.deleteById(id);
    }
}

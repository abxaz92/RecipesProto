package ru.macrobit.recept.commons;

import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Класс которые предоставляет интерфейс для обработки ошибок
 */
public class ExceptionFactory {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExceptionFactory.class);


    public static WebApplicationException exception(Response.Status status) {
        return new WebApplicationException(Response.status(status)
                .entity(new Error(status.getReasonPhrase())).build());
    }

    public static WebApplicationException exception(Response.Status status, Throwable cause) {
        LOG.warn("{} : {}", status.getStatusCode(), cause.toString());
        String resp = cause.getMessage() == null ? status.getReasonPhrase() : cause.getMessage();
        return new WebApplicationException(Response.status(status).entity(new Error(resp)).build());
    }

    public static WebApplicationException exception(Response.Status status, String cause) {
        LOG.warn("{} : {}", status.getStatusCode(), cause);
        return new WebApplicationException(Response.status(status)
                .entity(new Error(cause)).build());
    }

    public static Response getResponse(Response.Status status, String cause) {
        return Response.status(status)
                .entity(new Error(cause)).build();
    }
}

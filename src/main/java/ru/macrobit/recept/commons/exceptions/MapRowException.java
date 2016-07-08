package ru.macrobit.recept.commons.exceptions;

/**
 * Created by david on 7/8/16.
 */
public class MapRowException extends Exception {
    private static final long serialVersionUID = 1L;

    public MapRowException(String message) {
        super(message);
    }

    public MapRowException() {
        super();
    }
}
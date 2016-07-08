package ru.macrobit.recept.commons;

import net.iryndin.jdbf.core.DbfRecord;
import ru.macrobit.recept.commons.exceptions.MapRowException;

/**
 * Created by david on 7/8/16.
 */
public interface RowMapper<T> {
    T mapRow(DbfRecord dbfRecord) throws MapRowException;
}


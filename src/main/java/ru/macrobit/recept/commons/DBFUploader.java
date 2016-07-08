package ru.macrobit.recept.commons;

import net.iryndin.jdbf.core.DbfRecord;
import net.iryndin.jdbf.reader.DbfReader;
import ru.macrobit.recept.commons.exceptions.MapRowException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 7/8/16.
 */
public class DBFUploader {
    public static <T> List<T> precessData(DbfReader dbfReader, Charset charset, RowMapper<T> mapper) throws IOException {
        List<T> resultArray = new ArrayList<>();
        DbfRecord dbfRecord;
        while ((dbfRecord = dbfReader.read()) != null) {
            dbfRecord.setStringCharset(charset);
            T element;
            try {
                element = mapper.mapRow(dbfRecord);
                resultArray.add(element);
            } catch (MapRowException e) {
                e.printStackTrace();
            }
        }
        return resultArray;
    }
}

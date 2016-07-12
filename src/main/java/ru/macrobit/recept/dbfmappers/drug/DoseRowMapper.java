package ru.macrobit.recept.dbfmappers.drug;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.dbfmappers.DrugRowMapper;
import ru.macrobit.recept.pojo.drug.Dose;

import java.io.UnsupportedEncodingException;

/**
 * Created by david on 08.07.16.
 */
public class DoseRowMapper implements DbfRowMapper<Dose> {
    public static final Logger log = LoggerFactory.getLogger(DrugRowMapper.class);

    @Override
    public Dose mapRow(Object[] row) {
        Dose dose = new Dose();
        Long n;
        try {
            n = ((Number) row[0]).longValue();
            dose.setId(n);
        } catch (Exception e) {
            log.debug("{}", e);
        }
        try {
            dose.setName(new String(DbfUtils.trimLeftSpaces((byte[]) row[1]), "IBM866"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dose;
    }
}

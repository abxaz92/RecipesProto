package ru.macrobit.recept.dbfmappers.drug;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.drug.Weight;

import java.io.UnsupportedEncodingException;

/**
 * Created by david on 08.07.16.
 */
public class WeightRowMapper implements DbfRowMapper<Weight> {
    public static final Logger log = LoggerFactory.getLogger(VolumeRowMapper.class);

    @Override
    public Weight mapRow(Object[] row) {
        Weight dose = new Weight();
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

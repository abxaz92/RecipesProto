package ru.macrobit.recept.dbfmappers.drug;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.dbfmappers.DrugRowMapper;
import ru.macrobit.recept.pojo.drug.FarmGroup;

import java.io.UnsupportedEncodingException;

/**
 * Created by david on 08.07.16.
 */
public class FarmGroupRowMapper implements DbfRowMapper<FarmGroup> {
    public static final Logger log = LoggerFactory.getLogger(DrugRowMapper.class);

    @Override
    public FarmGroup mapRow(Object[] objects) {
        FarmGroup farmGroup = new FarmGroup();
        Long n;
        try {
            n = ((Number) objects[2]).longValue();
            farmGroup.setId(n);
        } catch (Exception e) {
            log.debug("{}", e);
        }
        try {
            farmGroup.setName(new String(DbfUtils.trimLeftSpaces((byte[]) objects[0]), "IBM866"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            farmGroup.setName(new String(DbfUtils.trimLeftSpaces((byte[]) objects[1]), "IBM866"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return farmGroup;
    }
    /*@Override
    public FarmGroup mapRow(DbfRecord dbfRecord) throws MapRowException {
        FarmGroup farmGroup = new FarmGroup();
        farmGroup.setId(dbfRecord.getBigDecimal("C_FARG").longValue());
        farmGroup.setName(dbfRecord.getString("SNAME_FRG"));
        farmGroup.setFname(dbfRecord.getString("FNAME_FRG"));
        return farmGroup;
    }*/
}
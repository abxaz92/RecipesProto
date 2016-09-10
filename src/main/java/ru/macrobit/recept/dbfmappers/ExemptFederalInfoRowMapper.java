package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.entities.FederalInfo;

import java.util.Date;

/**
 * Created by [david] on 10.09.16.
 */
public class ExemptFederalInfoRowMapper implements DbfRowMapper<FederalInfo> {
    private static final String ENCODING = "windows-1251";

    @Override
    public FederalInfo mapRow(Object[] row) {
        FederalInfo info = new FederalInfo();
        String snils = Recept.getString(row[0], ENCODING).replace(" ", "").replace("-", "");
        info.setSnils(snils);
        info.setBenefitDoc(Recept.getString(row[2], ENCODING));
        info.setBenefitDocNum(Recept.getString(row[3], ENCODING));
        Date date = Recept.getDate(row[4]);
        info.setDateLgBegin(date != null ? date.getTime() : null);
        date = Recept.getDate(row[5]);
        info.setDateLgEnd(date != null ? date.getTime() : null);
        return info;
    }
}

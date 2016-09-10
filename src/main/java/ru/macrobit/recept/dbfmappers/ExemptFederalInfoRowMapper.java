package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.entities.FederalInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by [david] on 10.09.16.
 */
public class ExemptFederalInfoRowMapper implements DbfRowMapper<FederalInfo> {
    private static final String ENCODING = "windows-1251";
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");


    @Override
    public FederalInfo mapRow(Object[] row) {
        FederalInfo info = new FederalInfo();
        String snils = Recept.getString(row[0], ENCODING).replace(" ", "").replace("-", "");
        info.setSnils(snils);
        info.setBenefitDoc(Recept.getString(row[2], ENCODING));
        info.setBenefitDocNum(Recept.getString(row[3], ENCODING));
        Date date = null;
        try {
            date = df.parse(Recept.getString(row[4], ENCODING));
        } catch (ParseException e) {
        }
        info.setDateLgBegin(date != null ? date.getTime() : null);
        try {
            date = df.parse(Recept.getString(row[5], ENCODING));
        } catch (ParseException e) {
        }
        info.setDateLgEnd(date != null ? date.getTime() : null);
        return info;
    }
}

package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Address;
import ru.macrobit.recept.pojo.Exempt;

import java.util.Date;

/**
 * Created by [david] on 09.09.16.
 */
public class ExemptMzRowMapper implements DbfRowMapper<Exempt> {
    private static final String ENCODING = "windows-1251";

    @Override
    public Exempt mapRow(Object[] row) {
        Exempt exe = new Exempt();
        exe.setFio(Recept.getString(row[3], ENCODING));
        Date date = Recept.getDate(row[4]);
        exe.setDateBirth(date != null ? date.getTime() : null);

        String snils = Recept.getString(row[5], ENCODING);
        snils = snils != null ? snils.replaceAll("-", "").replaceAll(" ", "") : null;

        exe.setPolisS(Recept.getString(row[6], ENCODING));
        exe.setPolisN(Recept.getString(row[7], ENCODING));
        Address a = new Address();
        try {
            a.setName(Recept.getString(row[8], ENCODING));
            String[] ads = a.getName().split(",");
            a.setRegion((ads[1]).replaceAll("РЕСП", "").trim());
            a.setStreet(ads[2].replaceAll("УЛ", "").trim());
            a.setHouse(ads[3].replaceAll("Д.", "").trim());
        } catch (Exception e) {
        }
        exe.setAddress(a);
//        Number number = Recept.getNumber(row[10]);
//        exe.setLpuId(number != null ? number.longValue() : null);
        exe.setDescription(Recept.getString(row[11], ENCODING));
        String docType = Recept.getString(row[13], ENCODING);

        try {
            String doc = Recept.getString(row[15], ENCODING).replaceAll(" ", "");
            if ("СВИДЕТЕЛЬСТВО О РОЖДЕНИИ".equals(docType)) {
                doc = doc.replaceAll("-", "").replaceAll("№", "");
                exe.setPasportNum(doc);
            } else {
                exe.setPasportSeries(doc.substring(0, 4));
                exe.setPasportNum(doc.substring(3));
            }

        } catch (Exception e) {
        }

        date = Recept.getDate(row[20]);
        exe.setDateLgBegin(date != null ? date.getTime() : null);

        date = Recept.getDate(row[21]);
        exe.setDateReg(date != null ? date.getTime() : null);
        exe.setGender(Recept.getString(row[22], ENCODING));
        exe.setType(ExemptType.MINZDRAV);

        if (Recept.isSnilsValid(snils)) {
            exe.setInvalid(false);
            return exe;
        } else {
            exe.setInvalid(true);
        }

        return exe;
    }
}

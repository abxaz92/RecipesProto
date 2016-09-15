package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Address;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptId;

import java.util.Date;

/**
 * Created by [david] on 09.09.16.
 */
public class ExemptMzRowMapper implements DbfRowMapper<Exempt> {
    private static final Logger logger = LoggerFactory.getLogger(ExemptMzRowMapper.class);
    private static final String ENCODING = "windows-1251";

    @Override
    public Exempt mapRow(Object[] row) {
        Exempt exempt = new Exempt();
        exempt.setFio(Recept.getString(row[3], ENCODING));
        Date date = Recept.getDate(row[4]);
        exempt.setDateBirth(date != null ? date.getTime() : null);

        String snils = Recept.getString(row[5], ENCODING);
        snils = snils != null ? snils.replaceAll("-", "").replaceAll(" ", "") : null;

        exempt.setPolisS(Recept.getString(row[6], ENCODING));
        exempt.setPolisN(Recept.getString(row[7], ENCODING));
        Address a = new Address();
        try {
            a.setName(Recept.getString(row[8], ENCODING));
            String[] ads = a.getName().split(",");
            a.setRegion((ads[1]).replaceAll("РЕСП", "").trim());
            a.setStreet(ads[2].replaceAll("УЛ", "").trim());
            a.setHouse(ads[3].replaceAll("Д.", "").trim());
        } catch (Exception e) {
        }
        exempt.setAddress(a);
//        Number number = Recept.getNumber(row[10]);
//        exe.setLpuId(number != null ? number.longValue() : null);
        exempt.setDescription(Recept.getString(row[11], ENCODING));
        String docType = Recept.getString(row[13], ENCODING);

        try {
            String doc = Recept.getString(row[15], ENCODING).trim();
            if ("СВИДЕТЕЛЬСТВО О РОЖДЕНИИ".equals(docType)) {
                doc = doc.replaceAll("-", "").replaceAll("№", "");
            }
            exempt.setDocumentNumber(doc.trim());
            exempt.doParseDocumentInfo();
            logger.info("{}, '{}:{}'", exempt.getDocumentNumber(), exempt.getDocSer(), exempt.getDocNum());

        } catch (Exception e) {
        }

        date = Recept.getDate(row[20]);
        exempt.setDateLgBegin(date != null ? date.getTime() : null);

        date = Recept.getDate(row[21]);
        exempt.setDateReg(date != null ? date.getTime() : null);
        exempt.setGender(Recept.getString(row[22], ENCODING));
        exempt.setCategoryId(Recept.getString(row[23], ENCODING));

        exempt.setSnils(snils);
        if (Recept.isSnilsValid(snils)) {
            exempt.setDoc(new ExemptId(exempt.getSnils(), ExemptType.MINZDRAV));
        } else if (exempt.getDocumentNumber() != null) {
            exempt.setDoc(new ExemptId(exempt.getDocumentNumber().replace(" ", ""), ExemptType.MINZDRAV));
        } else {
            exempt.setInvalid(true);
        }
        return exempt;
    }
}
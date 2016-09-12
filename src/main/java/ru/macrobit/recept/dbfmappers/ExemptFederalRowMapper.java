package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Address;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by [david] on 10.09.16.
 */
public class ExemptFederalRowMapper implements DbfRowMapper<Exempt> {
    private static final String ENCODING = "windows-1251";
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public Exempt mapRow(Object[] row) {
        Exempt exempt = new Exempt();
        String snils = Recept.getString(row[0], ENCODING).replace(" ", "").replace("-", "");
        exempt.setSnils(snils);

        String nameLast = (Recept.getString(row[2], ENCODING));
        String name = Recept.getString(row[3], ENCODING);
        String nameMiddle = Recept.getString(row[4], ENCODING);
        exempt.setFio(nameLast + " " + name + " " + nameMiddle);

        exempt.setGender(Recept.getString(row[5], ENCODING));
        Date date = null;
        try {
            date = df.parse(Recept.getString(row[6], ENCODING));
        } catch (ParseException e) {
        }
        exempt.setDateBirth(date != null ? date.getTime() : null);
        exempt.setDocumentNumber(Recept.getString(row[7], ENCODING));
        Address address = new Address();
        String addr = Recept.getString(row[9], ENCODING);
        String[] addrElems = addr.split(",");
        try {
            address.setName(addr);
            address.setZip(addrElems[0]);
            address.setRegion(addrElems[1]);
            address.setLocality(addrElems[2]);
            address.setStreet(addrElems[4]);
            address.setHouse(addrElems[5]);
            address.setKorpus(addrElems[6]);
            address.setAppartment(addrElems[7]);
        } catch (Exception e) {
        }

        exempt.setAddress(address);
        exempt.setDoc(new ExemptId(exempt.getSnils(), ExemptType.FEDERAL));
        exempt.setInvalid(!Recept.isSnilsValid(snils));
        return exempt;
    }
}

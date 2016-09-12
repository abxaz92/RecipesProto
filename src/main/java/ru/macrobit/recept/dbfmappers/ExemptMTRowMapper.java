package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Address;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptId;

import java.util.Date;

/**
 * Created by [david] on 09.09.16.
 */
public class ExemptMTRowMapper implements DbfRowMapper<Exempt> {
    private static final String ENCODING = "MacCyrillic";

    @Override
    public Exempt mapRow(Object[] row) {
        Exempt exe = new Exempt();

        exe.setAreaId(Recept.getString(row[0], ENCODING));
        // 1
        // 2
        String name = Recept.getString(row[3], ENCODING);
        String nameLast = Recept.getString(row[4], ENCODING);
        String nameMiddle = Recept.getString(row[5], ENCODING);

        exe.setFio(nameLast + " " + name + " " + nameMiddle);

        Date date = Recept.getDate(row[6]);
        exe.setDateBirth(date != null ? date.getTime() : null);

        exe.setGender(Recept.getString(row[7], ENCODING));

        Address address = new Address();
        address.setMst(Recept.getString(row[8], ENCODING));
        address.setRegion(Recept.getString(row[9], ENCODING));
        address.setStreet(Recept.getString(row[10], ENCODING));
        address.setHouse(Recept.getString(row[11], ENCODING));
        address.setKorpus(Recept.getString(row[12], ENCODING));
        address.setAppartment(Recept.getString(row[13], ENCODING));
        address.setZip(Recept.getString(row[14], ENCODING));
        exe.setAddress(address);

        exe.setDisabilityGroup(Recept.getString(row[15], ENCODING));
        // 16 - 33

        exe.setStatus(Recept.getString(row[34], ENCODING));

        date = Recept.getDate(row[35]);
        exe.setDateReg(date != null ? date.getTime() : null);
        // 36
        exe.setPasportSeries(Recept.getString(row[37], ENCODING));
        exe.setPasportNum(Recept.getString(row[38], ENCODING));

        // 39
        // 40

        exe.setPhone(Recept.getString(row[41], ENCODING));

        // 42
        // 43
        String snils = Recept.getString(row[45], ENCODING);

        exe.setInvalid(!Recept.isSnilsValid(snils));
        exe.setSnils(snils);
        exe.setDoc(new ExemptId(exe.getSnils(), ExemptType.MINTRUD));
        return exe;
    }
}

package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Address;
import ru.macrobit.recept.pojo.Exempt;

import java.util.Date;

/**
 * Created by david on 11.07.16.
 */
public class ExemptMintrudRowMapper implements DbfRowMapper<Exempt> {
    public static final Logger log = LoggerFactory.getLogger(ExemptMintrudRowMapper.class);
    private static final String ENCODING = "MacCyrillic";

    @Override
    public Exempt mapRow(Object[] row) {
        Exempt exe = new Exempt();

        try {
            exe.setAreaId(new String(DbfUtils.trimLeftSpaces((byte[]) row[0]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }
        // 1
        // 2
        String name = null;
        String nameLast = null;
        String nameMiddle = null;

        try {
            nameLast = new String(DbfUtils.trimLeftSpaces((byte[]) row[3]), ENCODING);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            name = new String(DbfUtils.trimLeftSpaces((byte[]) row[4]), ENCODING);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            nameMiddle = new String(DbfUtils.trimLeftSpaces((byte[]) row[5]), ENCODING);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        exe.setFio(nameLast + " " + name + " " + nameMiddle);


        try {
            Long l = ((Date) row[6]).getTime();
            exe.setDateBirth(l);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            exe.setGender(new String(DbfUtils
                    .trimLeftSpaces((byte[]) row[7]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        Address address = new Address();
        try {
            address.setMst(new String(DbfUtils.trimLeftSpaces((byte[]) row[8]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setRegion(new String(DbfUtils.trimLeftSpaces((byte[]) row[9]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setStreet(new String(DbfUtils.trimLeftSpaces((byte[]) row[10]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setHouse(new String(DbfUtils.trimLeftSpaces((byte[]) row[11]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setKorpus(new String(DbfUtils.trimLeftSpaces((byte[]) row[12]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setAppartment(new String(DbfUtils.trimLeftSpaces((byte[]) row[13]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            address.setZip(new String(DbfUtils.trimLeftSpaces((byte[]) row[14]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        exe.setAddress(address);

        try {
            exe.setDisabilityGroup(new String(DbfUtils.trimLeftSpaces((byte[]) row[15]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }
        // 16 - 33

        try {
            exe.setStatus(new String(DbfUtils.trimLeftSpaces((byte[]) row[34]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            Long l = ((Date) row[35]).getTime();
            l = (l > 0) ? l : null;
            exe.setDateReg(l);
        } catch (Exception e) {
            log.debug("{}", e);
        }
        // 36

        try {
            exe.setPasportSeries(new String(DbfUtils.trimLeftSpaces((byte[]) row[37]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
            log.error("37");
        }

        try {
            exe.setPasportNum(new String(DbfUtils.trimLeftSpaces((byte[]) row[38]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        // 39
        // 40

        try {
            exe.setPhone(new String(DbfUtils.trimLeftSpaces((byte[]) row[41]), ENCODING));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        // 42
        // 43

        String snils = null;
        try {
            snils = new String(DbfUtils.trimLeftSpaces((byte[]) row[45]), ENCODING);
        } catch (Exception e) {
            log.debug("{}", e);
        }


        if (Recept.isSnilsValid(snils)) {
            exe.setSnils(snils);
            exe.setType(ExemptType.MINTRUD);
            return exe;
        }
        return exe;
    }
}
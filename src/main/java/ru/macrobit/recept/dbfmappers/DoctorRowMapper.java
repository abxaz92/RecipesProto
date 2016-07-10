package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.Doctor;

import java.util.Date;

/**
 * Created by david on 7/10/16.
 */
public class DoctorRowMapper implements DbfRowMapper<Doctor> {
    public static final Logger log = LoggerFactory.getLogger(DoctorRowMapper.class);

    @Override
    public Doctor mapRow(Object[] row) {
        Doctor doc = new Doctor();
        Number n;
        try {
            n = ((Number) row[0]).longValue();
            doc.setOKATO(n.toString());
        } catch (Exception e) {
            log.debug("{}", e);
        }

        // 1

        try {
            doc.setCodeDoctorFull(new String(DbfUtils.trimLeftSpaces((byte[]) row[2]),
                    "windows-1251"));
            doc.setCodeDoctor(doc.getCodeDoctorFull().substring(
                    doc.getCodeDoctorFull().indexOf(' ') + 1));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            doc.setSecondName(new String(DbfUtils.trimLeftSpaces((byte[]) row[3]),
                    "windows-1251"));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            doc.setName(new String(DbfUtils.trimLeftSpaces((byte[]) row[4]), "windows-1251"));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            doc.setMiddleName(new String(DbfUtils.trimLeftSpaces((byte[]) row[5]),
                    "windows-1251"));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        doc.setFio(doc.getSecondName() + " " + doc.getName() + " " + doc.getMiddleName());

        try {
            doc.setOGRN_LPU(new String(DbfUtils.trimLeftSpaces((byte[]) row[6]), "windows-1251"));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        // 7

        try {
            doc.setSpeciality(new String(DbfUtils.trimLeftSpaces((byte[]) row[8]),
                    "windows-1251"));
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            Long l = ((Date) row[9]).getTime();
            l = (l > 0) ? l : null;
            doc.setDateOrder(l);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            doc.setDateCertificate(new String(DbfUtils.trimLeftSpaces((byte[]) row[10]),
                    "windows-1251"));
        } catch (Exception e) {
            log.debug("", e);
        }

        // 11

        // 12

        try {
            Long l = ((Date) row[13]).getTime();
            l = (l > 0) ? l : null;
            doc.setDateEntry(l);
        } catch (Exception e) {
            log.debug("", e);
        }

        try {
            Long l = ((Date) row[14]).getTime();
            l = (l > 0) ? l : null;
            doc.setDateRemoval(l);
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            doc.setDescription(new String(DbfUtils.trimLeftSpaces((byte[]) row[15]),
                    "windows-1251"));
        } catch (Exception e) {
            log.debug("", e);
        }

        try {
            n = ((Number) row[16]).longValue();
            doc.setId(n.longValue());
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            n = ((Number) row[17]).longValue();
            doc.setCodeSpeciality(n.toString());
        } catch (Exception e) {
            log.debug("{}", e);
        }

        try {
            n = ((Number) row[18]).longValue();
            doc.setLpuId(n.longValue());
        } catch (Exception e) {
            log.debug("{}", e);
        }
        return doc;
    }

}
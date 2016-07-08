package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;
import ru.macrobit.recept.pojo.Lpu;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by david on 7/8/16.
 */
public class LpuRowMapper implements DbfRowMapper<Lpu> {
    @Override
    public Lpu mapRow(Object[] row) {
        Lpu lpu = new Lpu();
        Number n;
        try {
            lpu.setId(((Float) row[0]).longValue());
        } catch (Exception e) {
        }

        try {
            lpu.setName(new String(DbfUtils.trimLeftSpaces((byte[]) row[1]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setCodeLPU(new String(DbfUtils.trimLeftSpaces((byte[]) row[2]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setInn(new String(DbfUtils.trimLeftSpaces((byte[]) row[3]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setFullName(new String(DbfUtils.trimLeftSpaces((byte[]) row[4]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setAddress(new String(DbfUtils.trimLeftSpaces((byte[]) row[5]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            n = ((Number) row[6]).longValue();
            lpu.setOKATO(n.toString());
        } catch (Exception e) {
        }

        try {
            n = ((Number) row[7]).longValue();
            lpu.setIndex(n.toString());
        } catch (Exception e) {
        }

        try {
            lpu.setHeadDocSecondName(new String(DbfUtils.trimLeftSpaces((byte[]) row[8]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setHeadDocName(new String(DbfUtils.trimLeftSpaces((byte[]) row[9]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setHeadDocMiddleName(new String(DbfUtils.trimLeftSpaces((byte[]) row[10]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setAccountantSecondName(new String(DbfUtils.trimLeftSpaces((byte[]) row[11]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setAccountantName(new String(DbfUtils.trimLeftSpaces((byte[]) row[12]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setAccountantMiddleName(new String(DbfUtils.trimLeftSpaces((byte[]) row[13]),
                    "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setPhone(new String(DbfUtils.trimLeftSpaces((byte[]) row[14]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setFax(new String(DbfUtils.trimLeftSpaces((byte[]) row[15]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            lpu.setEmail(new String(DbfUtils.trimLeftSpaces((byte[]) row[16]), "windows-1251"));
        } catch (Exception e) {
        }

        try {
            Long l = ((Date) row[17]).getTime();
            l = (l > 0) ? l : null;
            lpu.setDateB(l);
        } catch (Exception e) {
        }

        try {
            Long l = ((Date) row[18]).getTime();
            l = (l > 0) ? l : null;
            lpu.setDateE(l);
        } catch (Exception e) {
        }

        try {
            lpu.setCodeTFOMS(new String(DbfUtils.trimLeftSpaces((byte[]) row[19]),
                    "windows-1251"));
        } catch (UnsupportedEncodingException e) {
        }
        return lpu;
    }
}
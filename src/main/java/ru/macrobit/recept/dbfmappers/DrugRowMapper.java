package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.drug.*;

/**
 * Created by david on 07.07.16.
 */
public class DrugRowMapper implements DbfRowMapper<Drug> {
    public static final Logger log = LoggerFactory.getLogger(DrugRowMapper.class);

    @Override
    public Drug mapRow(Object[] column) {
        String encoding = "IBM866";
        Drug drug = new Drug();
        drug.setCode(Recept.getNumber(column[0]).longValue());
        drug.setDescription(Recept.getString(column[1], encoding));

        TorgName torgName = new TorgName();
        torgName.setId(Recept.getNumber(column[2]).longValue());
        drug.setTorgName(torgName);

        MnnName mnn = new MnnName();
        mnn.setId(Recept.getNumber(column[3]).longValue());
        drug.setMnnName(mnn);

        Form form = new Form();
        form.setId(Recept.getNumber(column[4]).longValue());
        drug.setForm(form);

        Dose dose = new Dose();
        dose.setId(Recept.getNumber(column[6]).longValue());
        drug.setDoseType(dose);

        drug.setDose(Recept.getString(column[5], encoding));
        drug.setDoseNum(Recept.getNumber(column[7]).floatValue());

        drug.setVolNum(Recept.getNumber(column[8]).floatValue());
        Volume vol = new Volume();
        vol.setId(Recept.getNumber(column[9]).longValue());
        drug.setVol(vol);

        drug.setWeightNum(Recept.getNumber(column[10]).floatValue());
        Weight weight = new Weight();
        weight.setId(Recept.getNumber(column[11]).longValue());
        drug.setWeight(weight);

        drug.setPack(String.valueOf(Recept.getNumber(column[12])));
        drug.setFirm(Recept.getString(column[13], encoding));
        drug.setFirmCountry(Recept.getString(column[14], encoding));
        drug.setPackFirm(Recept.getString(column[15], encoding));
        drug.setPackFirmCountry(Recept.getString(column[16], encoding));
        drug.setCompl(Recept.getString(column[17], encoding));

        FarmGroup farmGroup = new FarmGroup();
        farmGroup.setId(Recept.getNumber(column[18]).longValue());
        drug.setFarmGroup(farmGroup);

        drug.setFlagKek(Recept.getNumber(column[19]).longValue() == 1 ? true : false);
        drug.setFlagJVNLS(Recept.getNumber(column[20]).longValue() == 1 ? true : false);
        drug.setForbid(Recept.getNumber(column[21]).longValue() == 1 ? true : false);

        try {
            drug.setDateInclude(Recept.getDate(column[22]));
        } catch (Exception e) {
            log.error("{}", e);
        }

        try {
            drug.setDateExclude(Recept.getDate(column[22]));
        } catch (Exception e) {
            log.error("{}", e);
        }
        return drug;
    }

}

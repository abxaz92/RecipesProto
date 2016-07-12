package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.pojo.drug.Drug;
import ru.macrobit.recept.pojo.drug.Form;
import ru.macrobit.recept.pojo.drug.TorgName;

/**
 * Created by david on 07.07.16.
 */
public class DrugRowMapper implements DbfRowMapper<Drug> {
    @Override
    public Drug mapRow(Object[] objects) {
        Drug drug = new Drug();
    /*    drug.setCode(record.getBigDecimal("NOMK_LS").longValue());
        drug.setDescription(record.getString("NAME_MED"));

        TorgName torgName = new TorgName();
        torgName.setId(record.getBigDecimal("C_TRN").longValue());
        drug.setTorgName(torgName);

        Form form = new Form();
        form.setId(record.getBigDecimal("C_LF").longValue());
        drug.setForm(form);

        Dose dose = new Dose();
        dose.setId(record.getBigDecimal("C_DLS").longValue());
        drug.setDoseType(dose);

        drug.setDose(Float.parseFloat(record.getString("D_LS")));
        drug.setDoseNum(record.getBigDecimal("N_DOZA").longValue());

        Volume vol = new Volume();
        vol.setId(record.getBigDecimal("C_VLF").longValue());
        drug.setVol(vol);

        Weight weight = new Weight();
        weight.setId(record.getBigDecimal("C_MLF").longValue());
        drug.setWeight(weight);
        drug.setWeightNum(record.getBigDecimal("M_LF").longValue());

        drug.setPack(String.valueOf(record.getBigDecimal("N_FV")));
        drug.setFirm(record.getString("NAME_FCT"));
        drug.setFirmCountry(record.getString("NAME_CNF"));
        drug.setPackFirm(record.getString("NAME_PCK"));
        drug.setPackFirmCountry(record.getString("NAME_CNP"));
        drug.setCompl(record.getString("COMPL"));

        FarmGroup farmGroup = new FarmGroup();
        farmGroup.setId(record.getBigDecimal("C_FARG").longValue());
        drug.setFarmGroup(farmGroup);

        drug.setFlagKek(record.getBigDecimal("C_FARG").longValue() == 1 ? true : false);
        drug.setFlagJVNLS(record.getBigDecimal("FLAG1").longValue() == 1 ? true : false);
        drug.setForbid(record.getBigDecimal("FLAG2").longValue() == 1 ? true : false);

        try {
            drug.setDateInclude(record.getDate("DATE_B"));
        } catch (ParseException e) {
            LOG.error("{}", e);
        }

        try {
            drug.setDateExclude(record.getDate("DATE_E"));
        } catch (ParseException e) {
            LOG.error("{}", e);
        }*/

        return drug;
    }

}

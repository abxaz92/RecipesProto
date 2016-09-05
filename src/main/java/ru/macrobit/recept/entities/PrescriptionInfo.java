package ru.macrobit.recept.entities;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.macrobit.recept.commons.pjson.PGJsonObject;
import ru.macrobit.recept.pojo.Desease;
import ru.macrobit.recept.pojo.Doctor;
import ru.macrobit.recept.pojo.Exempt;

import java.util.List;

/**
 * Created by [david] on 05.09.16.
 */
@TypeDefs({@TypeDef(name = "PrescriptionInfoObject", typeClass = PrescriptionInfo.class)})
public class PrescriptionInfo extends PGJsonObject {
    private Desease desease; // заболевание
    private Doctor doctor; // врача
    private List<DrugAmount> drugs;
    private Exempt exempt;

    public Desease getDesease() {
        return desease;
    }

    public void setDesease(Desease desease) {
        this.desease = desease;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<DrugAmount> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugAmount> drugs) {
        this.drugs = drugs;
    }

    public Exempt getExempt() {
        return exempt;
    }

    public void setExempt(Exempt exempt) {
        this.exempt = exempt;
    }
    
    @Override
    public boolean isMutable() {
        return super.isMutable();
    }
}

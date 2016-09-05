package ru.macrobit.recept.entities;

import ru.macrobit.recept.pojo.drug.Drug;

/**
 * Created by [david] on 05.09.16.
 */
public class DrugAmount {
    private Drug drug;
    private double amount;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.drug.MnnName;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class MnnNameService extends AbstractDAO<MnnName> {
    public MnnNameService() {
        super("DRUG_MNAME", MnnName.class);
    }
}

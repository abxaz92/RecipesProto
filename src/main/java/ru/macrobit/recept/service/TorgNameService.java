package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.drug.TorgName;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class TorgNameService extends AbstractDAO<TorgName> {
    public TorgNameService() {
        super("DRUG_TNAME", TorgName.class);
    }
}

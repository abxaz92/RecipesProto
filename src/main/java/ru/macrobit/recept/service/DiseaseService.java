package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.Disease;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class DiseaseService extends AbstractDAO<Disease> {
    public DiseaseService() {
        super("disease", Disease.class);
    }
}

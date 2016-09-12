package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.ExemptCategory;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class ExemptCategoryService extends AbstractDAO<ExemptCategory> {
    public ExemptCategoryService() {
        super("exemptcategory", ExemptCategory.class);
    }
}

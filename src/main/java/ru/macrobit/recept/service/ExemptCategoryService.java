package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.ExemptCategory;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class ExemptCategoryService extends AbstractDAO<ExemptCategory> {
    public ExemptCategoryService() {
        super("exemptcategory", ExemptCategory.class);
    }

    public Map<String, ExemptCategory> getCategoryMap() {
        return ((List<ExemptCategory>) em.createQuery("select c from ExemptCategory c").getResultList())
                .stream()
                .collect(Collectors.toMap(ExemptCategory::getCode, Function.identity()));
    }
}

package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.Sourcefunding;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 25.07.16.
 */
@ApplicationScoped
public class SourcefundingService extends AbstractDAO<Sourcefunding> {
    public SourcefundingService() {
        super("Sourcefunding", Sourcefunding.class);
    }
}

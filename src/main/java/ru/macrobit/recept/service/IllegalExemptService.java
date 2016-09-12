package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.IllegalExempt;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 12.09.16.
 */
@ApplicationScoped
public class IllegalExemptService extends AbstractDAO<IllegalExempt> {
    public IllegalExemptService() {
        super("IllegalExempt", IllegalExempt.class);
    }
}

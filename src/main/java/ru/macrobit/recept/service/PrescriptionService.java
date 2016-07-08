package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.Prescription;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by david on 08.07.16.
 */
@ApplicationScoped
public class PrescriptionService extends AbstractDAO<Prescription> {

    public PrescriptionService() {
        super("prescription", Prescription.class);
    }
}

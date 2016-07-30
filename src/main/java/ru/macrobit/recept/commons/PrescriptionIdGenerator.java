package ru.macrobit.recept.commons;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by [david] on 30.07.16.
 */
public class PrescriptionIdGenerator implements IdentifierGenerator{
    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return null;
    }
}

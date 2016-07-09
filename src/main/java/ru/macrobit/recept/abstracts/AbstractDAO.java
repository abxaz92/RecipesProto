package ru.macrobit.recept.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.ExceptionFactory;
import ru.macrobit.recept.commons.Recept;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;

/**
 * Основной каркас для работы с базой данных
 */
public class AbstractDAO<T extends EntityInterface> extends ExceptionFactory {
    private static final Logger log = LoggerFactory.getLogger(AbstractDAO.class);

    @PersistenceContext
    protected EntityManager em;

    @Resource
    protected UserTransaction utx;

    private Class type;

    protected String tablename;

    public AbstractDAO(String tablename, Class type) {
        this.type = type;
        this.tablename = tablename;
    }

    public T findById(Long id) {
        return (T) em.find(type, id);
    }

    public void insert(T entity) throws Exception {
        persist(entity);
    }

    public void insert(List<T> entities) throws Exception {
        persist(entities);
    }

    public void deleteById(Long id) throws Exception {
        try {
            utx.begin();
            em.remove(findById(id));
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback();
        }

    }

    public Object findAll(Integer skip, Integer limit, String count) {
        if (count == null) {

            TypedQuery<T> query = em.createNamedQuery(tablename + ".getAll", type);
            if (skip != null)
                query.setFirstResult(skip);
            if (limit != null)
                query.setMaxResults(limit);
            return query.getResultList();
        } else {
            String sql = "SELECT COUNT(d) FROM " + tablename + " d";
            Query query = em.createQuery(sql);
            return query.getSingleResult();
        }
    }


    public void update(T entity) throws Exception {
        try {
            utx.begin();
            em.merge(entity);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback();
        }
    }

    public T update(Long id, JsonNode json) throws Exception {
        T old = findById(id);
        JsonNode res = Recept.merge(Recept.MAPPER.convertValue(old, JsonNode.class), json);
        T entity = null;
        try {
            entity = (T) Recept.MAPPER.treeToValue(res, this.type);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        update(entity);
        return entity;
    }

    private void persist(Object object) throws Exception {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback();
        }
    }
}

package ru.macrobit.recept.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
import java.util.Map;

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
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        entities.stream().forEach(session::save);
        session.getTransaction().commit();
        session.close();
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

    public List<T> findAll(Map<String, Object> queryMap, Integer skip, Integer limit, String sortProperties, String sortDirection) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(type);
        if (queryMap != null)
            combineCriteria(queryMap, criteria);
        if (skip != null)
            criteria.setFirstResult(skip);
        if (limit != null)
            criteria.setMaxResults(limit);

        if (sortProperties != null) {
            criteria.addOrder("asc".equals(sortDirection) ? Order.asc(sortProperties) : Order.desc(sortProperties));
        }
        List<T> list = criteria.list();
        session.close();
        return list;
    }

    public void combineCriteria(Map<String, Object> queryMap, Criteria criteria) {
        for (String key : queryMap.keySet()) {
            criteria.add(Restrictions.eq(key, queryMap.get(key)));
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

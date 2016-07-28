package ru.macrobit.recept.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.ExceptionFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
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

    public T findById(Object id) {
        return (T) em.find(type, id);
    }

    public T findById(Object id, User user) {
        try (Session session = em.unwrap(Session.class)) {
            return (T) em.find(type, id);
        }
    }

    public void insert(T entity) throws Exception {
        persist(entity);
    }

    public void insert(List<T> entities) {
        try (Session session = em.unwrap(Session.class)) {
            session.beginTransaction();
            entities.stream().forEach(session::save);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteById(Object id) {
        try {
            utx.begin();
            em.remove(findById(id));
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException();
        }

    }

    public void deleteById(Object id, User user) {
        try {
            utx.begin();
            em.remove(findById(id, user));
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException();
        }

    }

    public Object findAll(JSONObject jsonQuery, Integer skip, Integer limit, String count, String sortProperties, String sortDirection, User user) {
        try (Session session = em.unwrap(Session.class)) {
            Criteria criteria = session.createCriteria(type);
            if (jsonQuery != null)
                combineCriteria(jsonQuery, criteria);

            if (count != null) {
                return criteria.setProjection(Projections.rowCount()).uniqueResult();
            }
            if (skip != null)
                criteria.setFirstResult(skip);
            if (limit != null)
                criteria.setMaxResults(limit);

            if (sortProperties != null) {
                criteria.addOrder("asc".equals(sortDirection) ? Order.asc(sortProperties) : Order.desc(sortProperties));
            }
            List<T> list = criteria.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public static void combineCriteria(JSONObject jsonQuery, Criteria criteria) {
        jsonQuery.keySet().stream().forEach(key -> {
            criteria.add(Restrictions.eq(key, jsonQuery.get(key)));
        });
    }

    public void update(T entity) throws Exception {
        try {
            utx.begin();
            em.merge(entity);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback();
            throw new RuntimeException();
        }
    }

    public T update(Object id, JsonNode json, User user) throws Exception {
        T old = findById(id, user);
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
            throw new RuntimeException();
        }
    }

    protected Criteria getUserscopeCriteria(User user) {
        return null;
    }
}

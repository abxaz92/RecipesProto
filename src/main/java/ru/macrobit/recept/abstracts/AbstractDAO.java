package ru.macrobit.recept.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
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

    public T findById(Object id, User user) {
        if (user == null)
            return (T) em.find(type, id);
        try (Session session = em.unwrap(Session.class)) {
            Criteria criteria = getUserscopeCriteria(session, user);
            criteria.add(Restrictions.eq("id", id));
            return (T) criteria.uniqueResult();
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
            em.remove(findById(id, null));
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

    public Object findAll(JsonNode jsonQuery, Integer skip, Integer limit, String count, String sortProperties, String sortDirection, User user) {
        try (Session session = em.unwrap(Session.class)) {
            Criteria criteria = getUserscopeCriteria(session, user);
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

    public Object findAll(JsonNode jsonQuery, Integer skip, Integer limit, String count, String sortProperties, String sortDirection, User user, Class type) {
        try (Session session = em.unwrap(Session.class)) {
            Criteria criteria = getUserscopeCriteria(session, user, type);
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


    public static void combineCriteria(JsonNode jsonQuery, Criteria criteria) {
        jsonQuery.fields().forEachRemaining(jsonNodeEntry -> {
            JsonNode value = jsonNodeEntry.getValue();
            if (value.isArray()) {
                ArrayNode arrayNode = (ArrayNode) value;
                Object[] fields = new Object[arrayNode.size()];
                int i = 0;
                for (JsonNode jsonNode : value) {
                    fields[i] = Recept.castJsonValue(jsonNode);
                    i++;
                }
                criteria.add(Restrictions.in(jsonNodeEntry.getKey(), fields));
            } else if (!value.isObject()) {
                criteria.add(Restrictions.eq(jsonNodeEntry.getKey(), Recept.castJsonValue(value)));
            } else {
                Map.Entry<String, JsonNode> resctriction = value.fields().next();
                switch (resctriction.getKey()) {
                    case "$lt":
                        criteria.add(Restrictions.lt(jsonNodeEntry.getKey(), Recept.castJsonValue(resctriction.getValue())));
                        break;
                    case "$lte":
                        criteria.add(Restrictions.le(jsonNodeEntry.getKey(), Recept.castJsonValue(resctriction.getValue())));
                        break;
                    case "$gt":
                        criteria.add(Restrictions.gt(jsonNodeEntry.getKey(), Recept.castJsonValue(resctriction.getValue())));
                        break;
                    case "$gte":
                        criteria.add(Restrictions.ge(jsonNodeEntry.getKey(), Recept.castJsonValue(resctriction.getValue())));
                        break;
                    case "$regex":
                        criteria.add(Restrictions.ilike(jsonNodeEntry.getKey(), resctriction.getValue().asText(), MatchMode.ANYWHERE));
                        break;
                }
            }
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

    private Criteria getUserscopeCriteria(Session session, User user) {
        if (user == null)
            return session.createCriteria(type);
        Criteria criteria = session.createCriteria(type);
        Criterion criterion = getUserscopeCriterion(user);
        if (criterion != null)
            criteria.add(criterion);
        return criteria;
    }

    private Criteria getUserscopeCriteria(Session session, User user, Class type) {
        if (user == null)
            return session.createCriteria(type);
        Criteria criteria = session.createCriteria(type);
        Criterion criterion = getUserscopeCriterion(user);
        if (criterion != null)
            criteria.add(criterion);
        return criteria;
    }

    protected Criterion getUserscopeCriterion(User user) {
        return null;
    }
}

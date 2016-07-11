package ru.macrobit.recept.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONObject;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.ExemptMintrudRowMapper;
import ru.macrobit.recept.pojo.Exempt;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 11.07.16.
 */
@ApplicationScoped
public class ExemptService {
    private static final String tablename = "exempt";

    @PersistenceContext
    protected EntityManager em;

    @Resource
    protected UserTransaction utx;

    public Exempt findById(String id) {
        return em.find(Exempt.class, id);
    }

    public void insert(Exempt entity) throws Exception {
        persist(entity);
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

    public void insert(List<Exempt> entities) {
        try (Session session = em.unwrap(Session.class)) {
            session.beginTransaction();
            entities.stream().forEach(session::save);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteById(String id) {
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

    public List<Exempt> findAll(JSONObject jsonQuery, Integer skip, Integer limit, String sortProperties, String sortDirection) {
        try (Session session = em.unwrap(Session.class)) {
            Criteria criteria = session.createCriteria(Exempt.class);
            if (jsonQuery != null)
                AbstractDAO.combineCriteria(jsonQuery, criteria);
            if (skip != null)
                criteria.setFirstResult(skip);
            if (limit != null)
                criteria.setMaxResults(limit);

            if (sortProperties != null) {
                criteria.addOrder("asc".equals(sortDirection) ? Order.asc(sortProperties) : Order.desc(sortProperties));
            }
            List<Exempt> list = criteria.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public void update(Exempt entity) throws Exception {
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

    public Exempt update(String id, JsonNode json) throws Exception {
        Exempt old = findById(id);
        JsonNode res = Recept.merge(Recept.MAPPER.convertValue(old, JsonNode.class), json);
        Exempt entity = null;
        try {
            entity = (Exempt) Recept.MAPPER.treeToValue(res, Exempt.class);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        update(entity);
        return entity;
    }

    public Object uploadDBF(MultipartFormDataInput input) {
        List<Exempt> res = new ArrayList<>();
        input.getFormDataMap().forEach((key, val) -> val.stream().forEach(inputPart -> {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(inputStream, "/tmp/exempts.dbf"), new ExemptMintrudRowMapper());
                for (int i = 0; i < 100; i++) {
                    res.add(exempts.get(i));
                }
                try {
                    insert(exempts);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        return res;
    }
}

package ru.macrobit.recept.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
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

    // Получение подключения к базе данных
    @PersistenceContext
    protected EntityManager em;

    // получение интерфейса транзакций
    @Resource
    protected UserTransaction utx;

    // Тип сущности в которую будет преобразована строка из таблицы в бд
    private Class type;

    // название таблицы
    protected String tablename;

    // Конструктор класса
    public AbstractDAO(String tablename, Class type) {
        this.type = type;
        this.tablename = tablename;
    }

    // метод поиска сущности по id
    public T findById(Long id) {
        return (T) em.find(type, id);
    }

    // метод добавления сущности в бд
    public void insert(T entity) throws Exception {
        persist(entity);
    }

    // метод добавления массива сущностей в бд
    public void insert(List<T> entities) throws Exception {
        persist(entities);
    }

    // удаление сущности по id
    public void deleteById(Long id) throws Exception {
        try {
            utx.begin();  // начало транзакци
            em.remove(findById(id)); // удаление сущности
            utx.commit();  // завершение транзакции
        } catch (Exception e) {
            utx.rollback(); // в случае ошибки откат тразакции
        }

    }

    // метод для выборки всех документов из бд
    public Object findAll(Integer skip, Integer limit, String count) {
        // если аргумент выборки количества записей не используется, то выбираем сущности
        if (count == null) {

            // создание запроса
            TypedQuery<T> query = em.createNamedQuery(tablename + ".getAll", type);
            if (skip != null)
                query.setFirstResult(skip);
            if (limit != null)
                query.setMaxResults(limit);

            // оптравка запроса в бд и возвращене результата
            return query.getResultList();
        } else {
            String sql = "SELECT COUNT(d) FROM " + tablename + " d";
            Query query = em.createQuery(sql);
            return query.getSingleResult();
        }
    }


    // Обновление сущности
    public void update(T entity) throws Exception {
        try {
            utx.begin(); // начало транзакци
            em.merge(entity); // обнволение сущности
            utx.commit(); // завершение транзакции
        } catch (Exception e) {
            utx.rollback();
        }
    }

    // Обновление части сущности
    public T update(Long id, JsonNode json) throws Exception {
        // достаем старый документ из бд
        T old = findById(id);
        // накатываем обновленные поля на страрую сущность
        JsonNode res = Recept.merge(Recept.MAPPER.convertValue(old, JsonNode.class), json);
        T entity = null;
        try {
            entity = (T) Recept.MAPPER.treeToValue(res, this.type);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // обновляем сущность в бд
        update(entity);
        // возвращаем полученный результат
        return entity;
    }

    private void persist(Object object) throws Exception {
        try {
            utx.begin(); // начало транзакци
            em.persist(object); // запись сущности в бд
            utx.commit(); // завершение транзакции
        } catch (Exception e) {
            utx.rollback(); // в случае ошибки откат изменений
        }
    }
}

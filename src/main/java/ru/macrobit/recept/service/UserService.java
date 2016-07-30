package ru.macrobit.recept.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.User;
import ru.macrobit.recept.security.ContextService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by [david] on 19.07.16.
 */
@ApplicationScoped
public class UserService extends AbstractDAO<User> {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    ContextService ctx;

    public UserService() {
        super("users", User.class);
    }

    @Override
    public void insert(User user) throws Exception {
        user.setPassword(encodePassword(user.getPassword()));
        super.insert(user);
    }

    public void updatePassword(String userId, String newPass, User user) {
        try {
            User userUpd = findById(userId, user);
            utx.begin();
            userUpd.setPassword(encodePassword(newPass));
            em.merge(userUpd);
            utx.commit();
        } catch (Exception e) {
            throw exception(Status.INTERNAL_SERVER_ERROR, "Что-то пошло не так");
        }
    }

    @Override
    public User update(Object id, JsonNode json, User user) throws Exception {
        ((ObjectNode) json).remove("password");
        log.info("{}", json);
        return super.update(id, json, user);
    }

    public static final String encodePassword(String passString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passString.getBytes());
            bytes = java.util.Base64.getEncoder().encode(bytes);
            return new String(bytes, Charset.forName("UTF-8"));
        } catch (Exception e) {
            log.error("{}", e);
            return null;
        }
    }

    @Override
    protected Criterion getUserscopeCriterion(User user) {
        if (!ctx.isCurrentuserAdmin())
            return Restrictions.eq("lpu", user.getLpu());
        else return null;
    }
}

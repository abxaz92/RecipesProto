package ru.macrobit.recept.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.User;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response.Status;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by [david] on 19.07.16.
 */
@ApplicationScoped
public class UserService extends AbstractDAO<User> {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        super("users", User.class);
    }

    @Override
    public void insert(User user) throws Exception {
        user.setPassword(encodePassword(user.getPassword()));
        super.insert(user);
    }

    public void updatePassword(String userId, String newPass) {
        try {
            User user = findById(userId);
            utx.begin();
            user.setPassword(encodePassword(newPass));
            em.merge(user);
            utx.commit();
        } catch (Exception e) {
            throw exception(Status.INTERNAL_SERVER_ERROR, "Что-то пошло не так");
        }
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
}

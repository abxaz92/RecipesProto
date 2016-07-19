package ru.macrobit.recept.service;

import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.pojo.User;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by [david] on 19.07.16.
 */
@ApplicationScoped
public class UserService extends AbstractDAO<User> {
    public UserService() {
        super("users", User.class);
    }
}

package ru.macrobit.recept.security;

import ru.macrobit.recept.pojo.User;
import ru.macrobit.recept.service.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 * Created by [david] on 30.07.16.
 */
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ContextService {
    @Resource
    private SessionContext context;

    @Inject
    private UserService usersService;

    public String getCurrentUserName() {
        return context.getCallerPrincipal().getName();
    }

    public boolean isCurrentuserAdmin() {
        return context.isCallerInRole("ADMIN");
    }

    public boolean isCurrentuserInRole(String roleName) {
        return context.isCallerInRole(roleName);
    }

    public User getCurrentUser() {
        return usersService.findById(getCurrentUserName(), null);
    }
}

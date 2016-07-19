package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.*;
import java.util.List;

/**
 * Created by [david] on 19.07.16.
 */
@Entity
@Table(name = "USERS")
public class User implements EntityInterface {
    @Id
    private String username;
    private String password;

    @OneToMany(mappedBy = "username", targetEntity = Role.class, fetch = FetchType.EAGER)
    private List<Role> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

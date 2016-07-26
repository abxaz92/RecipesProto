package ru.macrobit.recept.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Lpu lpu;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @JoinTable(name = "USERROLES", joinColumns = @JoinColumn(name = "username"))
    @MapKey(name = "username")
    @Fetch(FetchMode.SELECT)
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Lpu getLpu() {
        return lpu;
    }

    public void setLpu(Lpu lpu) {
        this.lpu = lpu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

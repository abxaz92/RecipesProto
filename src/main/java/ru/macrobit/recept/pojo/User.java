package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.commons.JsonViews;

import javax.persistence.*;
import java.util.List;

/**
 * Created by [david] on 19.07.16.
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "USERS")
public class User implements EntityInterface {
    @Id
    @JsonView(JsonViews.Public.class)
    private String username;
    @JsonView(JsonViews.Internal.class)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonView(JsonViews.Public.class)
    private Lpu lpu;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @JoinTable(name = "USERROLES", joinColumns = @JoinColumn(name = "username"))
    @MapKey(name = "username")
    @Fetch(FetchMode.JOIN)
    @JsonView(JsonViews.Public.class)
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

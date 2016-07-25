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
    private String passwd;
    @ManyToOne(fetch = FetchType.EAGER)
    private Lpu lpu;

    @ElementCollection
    @CollectionTable(name = "USERROLES", joinColumns = @JoinColumn(name = "username"))
    @Fetch(FetchMode.JOIN)
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Lpu getLpu() {
        return lpu;
    }

    public void setLpu(Lpu lpu) {
        this.lpu = lpu;
    }
}
